create table ITEM_PEDIDO (
      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
      quantidade INT NOT NULL,
      descricao VARCHAR(120) NOT NULL,
      pedido_uuid BINARY(16) NOT NULL,
      FOREIGN KEY(pedido_uuid) REFERENCES PEDIDO(uuid_pedido)
)