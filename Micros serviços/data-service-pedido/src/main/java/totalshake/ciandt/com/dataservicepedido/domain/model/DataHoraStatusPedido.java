package totalshake.ciandt.com.dataservicepedido.domain.model;

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
    @JoinColumn(name = "pedido_uuid")
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

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public LocalDateTime getDataHoraCriado() {
        return dataHoraCriado;
    }

    public LocalDateTime getDataHoraRealizado() {
        return dataHoraRealizado;
    }

    public void setDataHoraCriado(LocalDateTime dataHoraCriado) {
        this.dataHoraCriado = dataHoraCriado;
    }

    public void setDataHoraRealizado(LocalDateTime dataHoraRealizado) {
        this.dataHoraRealizado = dataHoraRealizado;
    }

    public void setDataHoraCancelado(LocalDateTime dataHoraCancelado) {
        this.dataHoraCancelado = dataHoraCancelado;
    }

    public void setDataHoraPago(LocalDateTime dataHoraPago) {
        this.dataHoraPago = dataHoraPago;
    }

    public void setDataHoraConfirmado(LocalDateTime dataHoraConfirmado) {
        this.dataHoraConfirmado = dataHoraConfirmado;
    }

    public void setDataHoraPronto(LocalDateTime dataHoraPronto) {
        this.dataHoraPronto = dataHoraPronto;
    }

    public void setDataHoraSaiuParaEntrega(LocalDateTime dataHoraSaiuParaEntrega) {
        this.dataHoraSaiuParaEntrega = dataHoraSaiuParaEntrega;
    }

    public void setDataHoraEntrega(LocalDateTime dataHoraEntrega) {
        this.dataHoraEntrega = dataHoraEntrega;
    }

    public void setDataHoraPagamentoRecusado(LocalDateTime dataHoraPagamentoRecusado) {
        this.dataHoraPagamentoRecusado = dataHoraPagamentoRecusado;
    }
}
