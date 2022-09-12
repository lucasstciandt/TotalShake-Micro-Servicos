package totalshake.ciandt.com.dataservicepedido.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue
    @Column(name = "uuid_pedido",nullable = false)
    private UUID uuidPedido = UUID.randomUUID();

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

    public void adicionarItem(ItemPedido itemPedido){
        this.garantirNullSafetyItens();
        itemPedido.setPedido(this);
        itens.add(itemPedido);
    }

    public void registrarCriacao() {
        this.garantirNullSafetyDataHoraStatus();
        this.status = Status.CRIADO;
        this.dataHoraStatus.salvarDataHoraCriacao();
    }

    public void setUuidPedido(UUID uuidPedido) {
        this.uuidPedido = uuidPedido;
    }

    public void setUuidEntregador(UUID uuidEntregador) {
        this.uuidEntregador = uuidEntregador;
    }

    public UUID getUuidPedido() {
        return uuidPedido;
    }

    public DataHoraStatusPedido getDataHoraStatus() {
        return dataHoraStatus;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public Status getStatus() {
        return status;
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

    public void setUuidCliente(UUID uuidCliente) {
        this.uuidCliente = uuidCliente;
    }


    public void setUuidRestaurante(UUID uuidRestaurante) {
        this.uuidRestaurante = uuidRestaurante;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDataHoraStatus(DataHoraStatusPedido dataHoraStatus) {
        dataHoraStatus.setPedido(this);
        this.dataHoraStatus = dataHoraStatus;
    }

    @PrePersist
    public void setarUltimaAtualizacaoPedido(){
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    private void garantirNullSafetyItens() {
        if(itens == null){
            itens = new ArrayList<>();
        }
    }

    private void garantirNullSafetyDataHoraStatus() {
        if(this.dataHoraStatus == null){
            this.dataHoraStatus = new DataHoraStatusPedido();
            this.dataHoraStatus.setPedido(this);
        }
    }
}
