create table PEDIDO (
    uuid_pedido BINARY(16) PRIMARY KEY NOT NULL,
    uuid_cliente BINARY(16) NOT NULL,
    uuid_restaurante BINARY(16) NOT NULL,
    uuid_entregador BINARY(16),
    ultima_atualizacao DATETIME,
    status VARCHAR(20) NOT NULL,
    total DECIMAL(6,2)
)