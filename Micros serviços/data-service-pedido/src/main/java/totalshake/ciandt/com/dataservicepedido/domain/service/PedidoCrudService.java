package totalshake.ciandt.com.dataservicepedido.domain.service;

import org.springframework.stereotype.Service;
import totalshake.ciandt.com.dataservicepedido.controller.request.PedidoDTOPostRequest;
import totalshake.ciandt.com.dataservicepedido.controller.response.PedidoDTOResponse;
import totalshake.ciandt.com.dataservicepedido.domain.repository.PedidoRepository;

@Service
public class PedidoCrudService {

    private final PedidoRepository pedidoRepository;

    public PedidoCrudService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public PedidoDTOResponse criarPedido(PedidoDTOPostRequest pedidoDTOPost) {

        var pedido = pedidoDTOPost.toPedidoModel();
        pedido.registrarCriacao();

        pedidoRepository.save(pedido);

        return new PedidoDTOResponse(pedido.getUuidPedido());
    }
}
