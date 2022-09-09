package totalshake.ciandt.com.dataservicepedido.domain.model;

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
    private LocalDateTime dataHoraCriado;

    @Column(name = "data_hora_realizado")
    private LocalDateTime dataHoraRealizado;

    @Column(name = "data_hora_cancelado")
    private LocalDateTime dataHoraCancelado;

    @Column(name = "data_hora_pago")
    private LocalDateTime dataHoraPago;

    @Column(name = "data_hora_confirmado")
    private LocalDateTime dataHoraConfirmado;

    @Column(name = "data_hora_pronto")
    private LocalDateTime dataHoraPronto;

    @Column(name = "data_hora_saiu_para_entrega")
    private LocalDateTime dataHoraSaiuParaEntrega;

    @Column(name = "data_hora_entrega")
    private LocalDateTime dataHoraEntrega;


    @Column(name = "data_hora_pagamento_recusado")
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
}
