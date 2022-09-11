package totalshake.ciandt.com.apipedido.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DATA_HORA_STATUS_PEDIDO")
public class DataHoraStatusPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pedido_uuid", referencedColumnName = "uuid_pedido")
    private Pedido pedido;

    @Column(name = "data_hora_criado")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHoraCriado;

    @Column(name = "data_hora_realizado")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHoraRealizado;

    @Column(name = "data_hora_cancelado")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHoraCancelado;

    @Column(name = "data_hora_pago")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHoraPago;

    @Column(name = "data_hora_confirmado")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHoraConfirmado;

    @Column(name = "data_hora_pronto")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHoraPronto;

    @Column(name = "data_hora_saiu_para_entrega")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHoraSaiuParaEntrega;

    @Column(name = "data_hora_entrega")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHoraEntrega;


    @Column(name = "data_hora_pagamento_recusado")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHoraPagamentoRecusado;

    public void salvarDataHoraCriacao(){
        this.dataHoraCriado = LocalDateTime.now();
    }

    public void salvarDataHoraRealizado(){
        this.dataHoraRealizado = LocalDateTime.now();
    }

    public void salvarDataHoraCancelado(){
        this.dataHoraCancelado = LocalDateTime.now();
    }

    public LocalDateTime getDataHoraCriado() {
        return dataHoraCriado;
    }

    public LocalDateTime getDataHoraRealizado() {
        return dataHoraRealizado;
    }

    public LocalDateTime getDataHoraCancelado() {
        return dataHoraCancelado;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
