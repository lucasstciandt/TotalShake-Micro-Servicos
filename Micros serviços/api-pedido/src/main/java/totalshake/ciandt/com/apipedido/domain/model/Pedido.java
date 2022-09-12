package totalshake.ciandt.com.apipedido.domain.model;

import totalshake.ciandt.com.apipedido.application.errors.exceptions.ItemInexistenteException;
import totalshake.ciandt.com.apipedido.application.errors.CodInternoErroApi;
import totalshake.ciandt.com.apipedido.domain.service.state.EstadoPedido;
import totalshake.ciandt.com.apipedido.domain.service.state.EstadoPedidoFactory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue
    @Column(name = "uuid_pedido",nullable = false)
    private UUID uuidPedido;

    @Column(name = "uuid_cliente", nullable = false)
    private UUID uuidCliente;

    @Column(name = "uuid_restaurante", nullable = false)
    private UUID uuidRestaurante;

    @Column(name = "uuid_entregador")
    private UUID uuidEntregador;

    private BigDecimal total;

    @Column(name = "ultima_atualizacao")
    private LocalDateTime ultimaAtualizacao;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "pedido"
    )
    private DataHoraStatusPedido dataHoraStatus;


    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<ItemPedido> itens;

    @Transient
    private EstadoPedido estadoPedido;

    public void adicionarItem(ItemPedido itemPedido){
        this.garantirNullSafetyItens();
        itemPedido.setPedido(this);
        itens.add(itemPedido);
    }

    public void acrescentarItemDoPedido(long idItemPedido, int quantidade) {
        this.garantirNullSafetyItens();
        this.itens.stream()
                .filter(itemPedido -> itemPedido.getId() == idItemPedido)
                .findFirst()
                .ifPresentOrElse(
                        itemPedido -> itemPedido.acrescentarQuantidadeItem(quantidade),
                        () -> {
                            throw new ItemInexistenteException(
                                    CodInternoErroApi.AP202.getCodigo(),
                                    CodInternoErroApi.AP202.getMensagem()
                            );
                        }
                );
    }

    public void reduzirItemDoPedido(long idItemPedido, int quantidade) {
        this.garantirNullSafetyItens();
        this.itens.stream()
                .filter(itemPedido -> itemPedido.getId() == idItemPedido)
                .findFirst()
                .ifPresentOrElse(
                        itemPedido -> {
                            int qtdAtual = itemPedido.reduzirQuantidadeItem(quantidade);
                            if(qtdAtual <= 0){ this.itens.remove(itemPedido); }
                        },
                        () -> {
                            throw new ItemInexistenteException(
                                    CodInternoErroApi.AP202.getCodigo(),
                                    CodInternoErroApi.AP202.getMensagem()
                            );
                        }
                );
    }

    public void realizarPedido(){
        this.garantirNullSafetyEstadoPedido();
        this.estadoPedido.realizarPedido();
        this.garantirNullSafetyDataHoraStatus();
        this.dataHoraStatus.salvarDataHoraRealizado();
    }

    public void cancelarPedido(){
        this.garantirNullSafetyEstadoPedido();
        this.estadoPedido.cancelarPedido();
        this.garantirNullSafetyDataHoraStatus();
        this.dataHoraStatus.salvarDataHoraCancelado();
    }

    private void garantirNullSafetyItens() {
        if(itens == null){
            itens = new ArrayList<>();
        }
    }

    private void garantirNullSafetyEstadoPedido() {
        if(this.estadoPedido == null){
            this.estadoPedido = EstadoPedidoFactory.ofStatus(this.status, this);
        }
    }

    private void garantirNullSafetyDataHoraStatus() {
        if(this.dataHoraStatus == null){
            this.dataHoraStatus = new DataHoraStatusPedido();
            this.dataHoraStatus.setPedido(this);
        }
    }

    public DataHoraStatusPedido getDataHoraStatus() {
        if(this.dataHoraStatus == null){
            this.dataHoraStatus = new DataHoraStatusPedido();
        }
        return dataHoraStatus;
    }

    public EstadoPedido getEstadoPedido() {
        this.garantirNullSafetyEstadoPedido();
        return estadoPedido;
    }

    public void setUuidPedido(UUID uuidPedido) {
        this.uuidPedido = uuidPedido;
    }

    public void setUuidCliente(UUID uuidCliente) {
        this.uuidCliente = uuidCliente;
    }

    public void setUuidRestaurante(UUID uuidRestaurante) {
        this.uuidRestaurante = uuidRestaurante;
    }

    public void setUuidEntregador(UUID uuidEntregador) {
        this.uuidEntregador = uuidEntregador;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public void setDataHoraStatus(DataHoraStatusPedido dataHoraStatus) {
        dataHoraStatus.setPedido(this);
        this.dataHoraStatus = dataHoraStatus;
    }

    public UUID getUuidPedido() {
        return uuidPedido;
    }

    public UUID getUuidCliente() {
        return uuidCliente;
    }

    public UUID getUuidRestaurante() {
        return uuidRestaurante;
    }

    public UUID getUuidEntregador() {
        return uuidEntregador;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(uuidPedido, pedido.uuidPedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuidPedido);
    }
}
