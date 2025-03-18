package com.ecommerce.jwt.service;

import com.ecommerce.jwt.configuracion.JwtFiltroResquest;
import com.ecommerce.jwt.dao.CarritoDao;
import com.ecommerce.jwt.dao.PedidoProductoDao;
import com.ecommerce.jwt.dao.ProductoDao;
import com.ecommerce.jwt.dao.UsuarioDao;
import com.ecommerce.jwt.model.*;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RealizarPedidoProductoService {

    private static final String REALIZADO_PEDIDO="Realizado";

    private static final String CLAVE="rzp_test_1kOTLqKCm2Xwm3";
    private static final String CLAVE_SECRETA="hSpCWIVp0VrW4IqCDx0eg35m";
    private static final String MONEDA="INR";//USD

    @Autowired
    private PedidoProductoDao pedidoProductoDao;

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JwtFiltroResquest jwtFiltroResquest;

    @Autowired
    private CarritoDao carritoDao;

    public List<PedidoProducto> obteniendoDetallesPedido(){
        String usuarioActual = JwtFiltroResquest.USUARIO_ACTUAL;
        Usuario usuario = usuarioDao.findById(usuarioActual).get();

        return pedidoProductoDao.findByUsuario(usuario);
    }

    public List<PedidoProducto> obteniendoTodosLosDetallesPedido(String estado){
        List<PedidoProducto> pedidoDetalles = new ArrayList<>();

        if (estado.equals("Todo")){//este parametro "Toda" lo usamos para listar en el Postman.
            pedidoProductoDao.findAll().forEach(
                    x->pedidoDetalles.add(x)
            );
        } else {
            pedidoProductoDao.findByEstadoPedido(estado).forEach(
                    x->pedidoDetalles.add(x)
            );
        }
        return pedidoDetalles;
    }

    public void lugarPedido(OrdenarEntradaPedido ordenarEntradaPedido, boolean esPagoDeUnSoloProducto){// esto "esPagoDeUnSoloProducto" lo estamos usando solo para poder eliminar los reservados en carrito cuando ya hace la compra el usuario
        List<CantidadPedidoProducto> cantidadPedidoProductos = ordenarEntradaPedido.getCantidadPedidoProductos();

        for (CantidadPedidoProducto o: cantidadPedidoProductos) {
            Producto producto = productoDao.findById(o.getProductoId()).get();

           String usuarioActual = jwtFiltroResquest.USUARIO_ACTUAL;
           Usuario usuario = usuarioDao.findById(usuarioActual).get();

            PedidoProducto pedidoProducto= new PedidoProducto(
                    ordenarEntradaPedido.getNombreCompleto(),
                    ordenarEntradaPedido.getDireccion(),
                    ordenarEntradaPedido.getNumeroContacto(),
                    ordenarEntradaPedido.getNumeroAlternoContacto(),
                    REALIZADO_PEDIDO,
                    producto.getDescuentoPrecioProducto() * o.getCantidad(),
                    producto,
                    usuario,
                    ordenarEntradaPedido.getTransactionId()
            );
                    //esto es para vaciar las reservas hechos en el carrito
            if(!esPagoDeUnSoloProducto) {
                List<Carrito> carts = carritoDao.findByUsuario(usuario);
                carts.stream().forEach(x -> carritoDao.deleteById(x.getCarritoId()));
            }
            pedidoProductoDao.save(pedidoProducto);
        }
    }
	
	public void marcarComoEntregado(Integer pedidoId){
			PedidoProducto pedidoProducto = pedidoProductoDao.findById(pedidoId).get();
			
			if(pedidoProducto!=null){
			pedidoProducto.setEstadoPedido("Entregado");//aqui cambiara de estado de Realizado a Entregado
			pedidoProductoDao.save(pedidoProducto);
			}
	}

    public DetalleTransaccion crearTransacciones(Double monto){
        try {
            JSONObject jsonObjeto=new JSONObject();
            jsonObjeto.put("amount",(monto*100));//esto "amount" tiene que ser asi no lo puedo cambiar a español porque RazorPay no lo permite
            jsonObjeto.put("currency", MONEDA);//aqui igual "currency" no puede ser en español

            RazorpayClient razorpayCliente= new RazorpayClient(CLAVE, CLAVE_SECRETA);

            Order pedido= razorpayCliente.orders.create(jsonObjeto);

            DetalleTransaccion detalleTransaccion = prepararTransaccionDetalle(pedido);
            return detalleTransaccion;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private DetalleTransaccion prepararTransaccionDetalle(Order pedido){
        String pedidoId = pedido.get("id");
        String moneda = pedido.get("currency");//aqui igual "currency" no puede ser en español
        Integer cantidad = pedido.get("amount");//esto "amount" tiene que ser asi no lo puedo cambiar a español porque RazorPay no lo permite

        DetalleTransaccion detalleTransaccion = new DetalleTransaccion(pedidoId,moneda,cantidad,CLAVE);
        return detalleTransaccion;
    }
}
