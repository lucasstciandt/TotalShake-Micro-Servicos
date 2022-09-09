package totalshake.ciandt.com.dataservicepedido.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import totalshake.ciandt.com.dataservicepedido.application.controller.request.PedidoDTOPostRequest;
import totalshake.ciandt.com.dataservicepedido.application.controller.response.PedidoDTOResponse;
import totalshake.ciandt.com.dataservicepedido.application.error.ApiErroCodInternoMensagem;
import totalshake.ciandt.com.dataservicepedido.application.error.exceptions.PedidoInexistenteException;
import totalshake.ciandt.com.dataservicepedido.domain.model.Pedido;
import totalshake.ciandt.com.dataservicepedido.domain.repository.PedidoRepository;

import java.util.UUID;

@Service
public class PedidoCrudService {

    private final PedidoRepository pedidoRepository;

    public PedidoCrudService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public PedidoDTOResponse criarPedido(PedidoDTOPostRequest pedidoDTOPost) {

        var pedido = pedidoDTOPost.toPedidoModel();
        pedido.registrarCriacao();

        pedido = pedidoRepository.save(pedido);

        return new PedidoDTOResponse(pedido.getUuidPedido());
    }

    public PedidoDTOResponse buscarPedidoDto(UUID uuidPedido){
        var pedido = this.buscarPedidoPorId(uuidPedido);

        return new PedidoDTOResponse(
                pedido.getUuidPedido(),
                pedido.getUuidCliente(),
                pedido.getUuidRestaurante(),
                pedido.getUuidEntregador(),
                pedido.getStatus(),
                pedido.getTotal(),
                pedido.getUltimaAtualizacao(),
                pedido.getItens(),
                pedido.getDataHoraStatus()
        );
    }

    public Pedido buscarPedidoPorId(UUID uuidPedido) {
        return pedidoRepository
                .findById(uuidPedido)
                .orElseThrow(
                        () -> new PedidoInexistenteException(
                                ApiErroCodInternoMensagem.DSP002.getCodigo(),
                                ApiErroCodInternoMensagem.DSP002.getMensagem()
                        )
                );
    }
}
