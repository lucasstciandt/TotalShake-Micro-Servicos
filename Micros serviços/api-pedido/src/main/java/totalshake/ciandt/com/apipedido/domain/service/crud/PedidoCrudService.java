package totalshake.ciandt.com.apipedido.domain.service.crud;

import totalshake.ciandt.com.apipedido.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import totalshake.ciandt.com.apipedido.proxy.DataServicePedidoProxy;
import totalshake.ciandt.com.apipedido.proxy.response.PedidoDTOGetResponse;

import java.util.UUID;

@Service
public class PedidoCrudService {

    private final PedidoRepository pedidoRepository;
    private final DataServicePedidoProxy dataServicePedidoProxy;

    public PedidoCrudService(PedidoRepository pedidoRepository, DataServicePedidoProxy dataServicePedidoProxy) {
        this.pedidoRepository = pedidoRepository;
        this.dataServicePedidoProxy = dataServicePedidoProxy;
    }

    public PedidoDTOGetResponse acrescentarItem(UUID pedidoId, Long itemId, Integer quantidade){
        var pedidoDto = this.dataServicePedidoProxy.buscarPedido(pedidoId);

        var pedidoModel = pedidoDto.toPedidoModel();
        pedidoModel.acrescentarItemDoPedido(itemId, quantidade);

        //todo Usar o data Service para salvar o resultado da operação de negócio
        pedidoModel = this.pedidoRepository.save(pedidoModel);

        return new PedidoDTOGetResponse(
                pedidoModel.getUuidPedido(),
                pedidoModel.getUuidCliente(),
                pedidoModel.getUuidRestaurante(),
                pedidoModel.getStatus(),
                pedidoModel.getUltimaAtualizacao(),
                pedidoModel.getItens()
        );
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

    public PedidoDTOResponse reduzirQuantidadeItem(Long pedidoId, Long itemId, int quantidade) {
        var pedido = this.buscarPedidoPorId(pedidoId);
        pedido.reduzirItemDoPedido(itemId, quantidade);
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
