package totalshake.ciandt.com.apipagamento.domain.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "PAGAMENTO")
public class Pagamento {

    @Id
    @GeneratedValue
    @Column(name = "uuid_pagamento",nullable = false)
    private UUID uuidPagamento =  UUID.randomUUID();

    @Column(name = "uuid_pedido", nullable = false)
    private UUID uuidPedido;

    @Column(name = "uuid_cliente", nullable = false)
    private UUID uuidCliente;

    @Column(name = "uuid_restaurante", nullable = false)
    private UUID uuidRestaurante;

    @Column(name = "data_hora_pagamento", nullable = false)
    private LocalDateTime dataHoraPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", nullable = false)
    private FormaPagamento formaPagamento;

    public Status getStatus() {
        return status;
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

    public void setDataHoraPagamento(LocalDateTime dataHoraPagamento) {
        this.dataHoraPagamento = dataHoraPagamento;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
