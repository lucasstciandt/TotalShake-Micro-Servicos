package totalshake.ciandt.com.apipedido.domain.service.crud;

import org.springframework.stereotype.Service;
import totalshake.ciandt.com.apipedido.application.controller.request.ItemPedidoDTO;
import totalshake.ciandt.com.apipedido.proxy.DataServicePedidoProxy;
import totalshake.ciandt.com.apipedido.proxy.put.AtualizacaoPedidoCompletaDTORequest;
import totalshake.ciandt.com.apipedido.proxy.put.response.PedidoDTOGetResponse;

import java.util.UUID;

@Service
public class PedidoCrudService {

    private final DataServicePedidoProxy dataServicePedidoProxy;

    public PedidoCrudService(DataServicePedidoProxy dataServicePedidoProxy) {
        this.dataServicePedidoProxy = dataServicePedidoProxy;
    }

    public PedidoDTOGetResponse acrescentarItem(UUID pedidoId, Long itemId, Integer quantidade){
        var pedidoDto = this.dataServicePedidoProxy.buscarPedido(pedidoId);

        var pedidoModel = pedidoDto.toPedidoModel();

        pedidoModel.acrescentarItemDoPedido(itemId, quantidade);

        var pedidoDtoAtualizacao = new AtualizacaoPedidoCompletaDTORequest(
                pedidoModel.getUuidPedido(),
                pedidoModel.getUuidCliente(),
                pedidoModel.getUuidRestaurante(),
                pedidoModel.getUuidEntregador(),
                pedidoModel.getStatus(),
                pedidoModel.getTotal(),
                pedidoModel.getItens(),
                pedidoModel.getDataHoraStatus()
        );

        return this.dataServicePedidoProxy.atualizarPedido(pedidoDtoAtualizacao);
    }

    public PedidoDTOGetResponse reduzirQuantidadeItem(UUID pedidoId, Long itemId, int quantidade) {
        var pedidoDto = this.dataServicePedidoProxy.buscarPedido(pedidoId);
        var pedidoModel = pedidoDto.toPedidoModel();

        pedidoModel.reduzirItemDoPedido(itemId, quantidade);

        var pedidoDtoAtualizacao = new AtualizacaoPedidoCompletaDTORequest(
                pedidoModel.getUuidPedido(),
                pedidoModel.getUuidCliente(),
                pedidoModel.getUuidRestaurante(),
                pedidoModel.getUuidEntregador(),
                pedidoModel.getStatus(),
                pedidoModel.getTotal(),
                pedidoModel.getItens(),
                pedidoModel.getDataHoraStatus()
        );

        return this.dataServicePedidoProxy.atualizarPedido(pedidoDtoAtualizacao);
    }

    public PedidoDTOGetResponse adicionarItemNoPedido(UUID pedidoId, ItemPedidoDTO itemPedidoDTO) {
        var pedidoDto = this.dataServicePedidoProxy.buscarPedido(pedidoId);
        var pedidoModel = pedidoDto.toPedidoModel();

        var itemPedido = itemPedidoDTO.toItemPedidoModel();
        pedidoModel.adicionarItem(itemPedido);

        var pedidoDtoAtualizacao = new AtualizacaoPedidoCompletaDTORequest(
                pedidoModel.getUuidPedido(),
                pedidoModel.getUuidCliente(),
                pedidoModel.getUuidRestaurante(),
                pedidoModel.getUuidEntregador(),
                pedidoModel.getStatus(),
                pedidoModel.getTotal(),
                pedidoModel.getItens(),
                pedidoModel.getDataHoraStatus()
        );

        return this.dataServicePedidoProxy.atualizarPedido(pedidoDtoAtualizacao);
    }

    public PedidoDTOGetResponse realizarPedido(UUID pedidoId) {
        var pedidoDto  = this.dataServicePedidoProxy.buscarPedido(pedidoId);

        var pedidoModel = pedidoDto.toPedidoModel();
        pedidoModel.realizarPedido();

        var pedidoDtoAtualizacao = new AtualizacaoPedidoCompletaDTORequest(
                pedidoModel.getUuidPedido(),
                pedidoModel.getUuidCliente(),
                pedidoModel.getUuidRestaurante(),
                pedidoModel.getUuidEntregador(),
                pedidoModel.getStatus(),
                pedidoModel.getTotal(),
                pedidoModel.getItens(),
                pedidoModel.getDataHoraStatus()
        );

        return this.dataServicePedidoProxy.atualizarPedido(pedidoDtoAtualizacao);
    }

    public PedidoDTOGetResponse cancelarPedido(UUID pedidoId) {
        var pedidoDto  = this.dataServicePedidoProxy.buscarPedido(pedidoId);

        var pedidoModel = pedidoDto.toPedidoModel();
        pedidoModel.cancelarPedido();

        var pedidoDtoAtualizacao = new AtualizacaoPedidoCompletaDTORequest(
                pedidoModel.getUuidPedido(),
                pedidoModel.getUuidCliente(),
                pedidoModel.getUuidRestaurante(),
                pedidoModel.getUuidEntregador(),
                pedidoModel.getStatus(),
                pedidoModel.getTotal(),
                pedidoModel.getItens(),
                pedidoModel.getDataHoraStatus()
        );

        return this.dataServicePedidoProxy.atualizarPedido(pedidoDtoAtualizacao);
    }

}
