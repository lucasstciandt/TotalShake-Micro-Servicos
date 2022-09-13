package totalshake.ciandt.com.apipagamento.domain.model;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate dataHoraPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", nullable = false)
    private FormaPagamento formaPagamento;
}
