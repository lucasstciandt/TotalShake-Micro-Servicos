package totalshake.ciandt.com.dataservicepedido.domain.service;

import org.springframework.stereotype.Service;
import totalshake.ciandt.com.dataservicepedido.application.controller.request.AtualizacaoPedidoDTORequest;
import totalshake.ciandt.com.dataservicepedido.application.controller.request.PedidoDTOPostRequest;
import totalshake.ciandt.com.dataservicepedido.application.controller.response.PedidoDTOResponse;
import totalshake.ciandt.com.dataservicepedido.application.error.ApiErroCodInternoMensagem;
import totalshake.ciandt.com.dataservicepedido.application.error.exceptions.PedidoInexistenteException;
import totalshake.ciandt.com.dataservicepedido.domain.model.DataHoraStatusPedido;
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

    public PedidoDTOResponse atualizarPedido(UUID uuidPedido, AtualizacaoPedidoDTORequest atualizacaoDtoRequest) {
        var pedido = this.buscarPedidoPorId(uuidPedido);
        var dataHoraStatusPedidoAtualizado = atualizarDataHoraStatus(atualizacaoDtoRequest, pedido);

        pedido.setStatus(atualizacaoDtoRequest.status());
        pedido.setDataHoraStatus(dataHoraStatusPedidoAtualizado);

        pedido = pedidoRepository.save(pedido);

        return new PedidoDTOResponse(
                pedido.getUuidPedido(),
                pedido.getStatus(),
                pedido.getUltimaAtualizacao(),
                pedido.getDataHoraStatus()
        );
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

    private  DataHoraStatusPedido atualizarDataHoraStatus(AtualizacaoPedidoDTORequest atualizacaoDtoRequest, Pedido pedido) {
        var dataHoraStatusPedido = pedido.getDataHoraStatus();

        dataHoraStatusPedido.setDataHoraRealizado(atualizacaoDtoRequest.dataHoraRealizado());
        dataHoraStatusPedido.setDataHoraPago(atualizacaoDtoRequest.dataHoraPago());
        dataHoraStatusPedido.setDataHoraConfirmado(atualizacaoDtoRequest.dataHoraConfirmado());
        dataHoraStatusPedido.setDataHoraPronto(atualizacaoDtoRequest.dataHoraPronto());
        dataHoraStatusPedido.setDataHoraSaiuParaEntrega(atualizacaoDtoRequest.dataHoraSaiuParaEntrega());
        dataHoraStatusPedido.setDataHoraEntrega(atualizacaoDtoRequest.dataHoraEntrega());
        dataHoraStatusPedido.setDataHoraCancelado(atualizacaoDtoRequest.dataHoraCancelado());
        dataHoraStatusPedido.setDataHoraPagamentoRecusado(atualizacaoDtoRequest.dataHoraPagamentoRecusado());

        return dataHoraStatusPedido;
    }

}
