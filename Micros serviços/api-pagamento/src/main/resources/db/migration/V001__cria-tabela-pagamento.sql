create table PAGAMENTO (
    uuid_pagamento BINARY(16) PRIMARY KEY NOT NULL,
    uuid_pedido BINARY(16) NOT NULL,
    uuid_cliente BINARY(16) NOT NULL,
    uuid_restaurante BINARY(16) NOT NULL,
    data_hora_pagamento DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    forma_pagamento VARCHAR(20) NOT NULL
)