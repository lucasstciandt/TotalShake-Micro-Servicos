package totalshake.ciandt.com.apipedido.domain.service.crud;

import org.springframework.stereotype.Service;
import totalshake.ciandt.com.apipedido.proxy.DataServicePedidoProxy;
import totalshake.ciandt.com.apipedido.proxy.dataservicepedido.put.AtualizacaoPedidoCompletaDTORequest;
import totalshake.ciandt.com.apipedido.proxy.dataservicepedido.put.response.PedidoDTOGetResponse;

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


    /*
        public PedidoDTOResponse realizarPedido(Long idPedido) {
            var pedido = buscarPedidoPorId(idPedido);
            pedido.realizarPedido();
            pedido = pedidoRepository.save(pedido);

            return new PedidoDTOResponse(pedido);
        }

        public PedidoDTOResponse cancelarPedido(Long idPedido) {
            var pedido = this.buscarPedidoPorId(idPedido);
            pedido.cancelarPedido();
            pedido = pedidoRepository.save(pedido);

            return new PedidoDTOResponse(pedido);
        }



    public PedidoDTOResponse adicionarItemNoPedido(Long pedidoId, ItemPedidoDTO itemPedidoDTO) {
        var pedido = this.buscarPedidoPorId(pedidoId);
        var itemPedido = itemPedidoDTO.toItemPedidoModel();
        pedido.adicionarItem(itemPedido);
        pedido = pedidoRepository.save(pedido);

        return new PedidoDTOResponse(pedido);
    }*/

}
