package main;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.TbCategoria;
import model.TbProducto;
import model.TbProveedor;
import model.TbTipo;
import model.TbUsuario;


public class ProductoMAVEN {

	public static void main(String[] args) {
		
		ValidarAcceso("U001@gmail.com","10001");
		//EliminarProducto("POO19");
		//ActualizarProducto();
		//GrabarProducto();
		//BuscarProducto("P0002");
		//ListarProductos();
		//MostrarNroProductos();
	}

	public static void MostrarNroProductos(){
		System.out.println("===JPA CRUD====");
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("Propuesto1");
		
		EntityManager em = fabrica.createEntityManager();
		
		List<TbProducto>lstProducto = em.createQuery("Select a From TbProducto a", TbProducto.class).getResultList();
		System.out.println("==============================================");
		/*Mostrar cantidad de Productos*/
		System.out.println("nro de Productos:" +lstProducto.size());
		System.out.println("==============================================");
	}
	
	public static void ListarProductos(){
		System.out.println("===JPA CRUD====");
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("Propuesto1");
		
		EntityManager em = fabrica.createEntityManager(); 
		
		List<TbProducto>lstProducto = em.createQuery("Select a From TbProducto a", TbProducto.class).getResultList();
		 System.out.println("==============================================");
		    System.out.println("Lista de productos:");
		    System.out.println("----------------------------------------------");
		    System.out.printf("%-10s %-20s %-10s %-10s %-10s %-10s %-20s%n",
		                      "IdProd", "Descripcion", "Stok", "Precio", "Categoria", "Estado", "Proveedor");
		    
		    for (TbProducto p : lstProducto) {
		        System.out.printf("%-10s %-20s %-10s %-10s %-10s %-10s %-20s%n",
		                          p.getIdProd(), p.getDesProd(), p.getStkProd(),
		                          p.getPreProd(), p.getTbCategoria().getIdcategoria(),
		                          p.getEstProd(), p.getTbProveedor().getIdproveedor());
		    }
		    
		    System.out.println("======================================================================");
		}
	
	public static void BuscarProducto(String prod){
		System.out.println("===JPA CRUD====");
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("Propuesto1");
		
		EntityManager em = fabrica.createEntityManager();
		
		/*Búsqueda de producto por ID*/
		System.out.println("Buscar Producto con id" + prod);
		System.out.println("-----------------------");
		TbProducto p = em.find(TbProducto.class,prod);
		
		 	System.out.printf("%-10s: %-20s%n", "IdProd", p.getIdProd());
		    System.out.printf("%-10s: %-20s%n", "Descripcion", p.getDesProd());
		    System.out.printf("%-10s: %-20s%n", "Stock", p.getStkProd());
		    System.out.printf("%-10s: %-20s%n", "Precio", p.getPreProd());
		    System.out.printf("%-10s: %-20s%n", "Categoria", p.getTbCategoria().getIdcategoria());
		    System.out.printf("%-10s: %-20s%n", "Estado", p.getEstProd());
		    System.out.printf("%-10s: %-20s%n", "Proveedor", p.getTbProveedor().getIdproveedor());
		    
		    System.out.println("==============================================");
		
	}
	
	public static void GrabarProducto(){
		System.out.println("===JPA CRUD====");
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("Propuesto1");
		
		EntityManager em = fabrica.createEntityManager();
		
		/*Insertar nuevo usuario*/
		System.out.println("Grabar nuevo Producto: ");
		System.out.println("-------------------------------------------------------------------------");
	
		em.getTransaction().begin();
		TbProducto prod = new TbProducto();
		TbCategoria cate = em.find(TbCategoria.class, 2);//IdCategoria: Buscar en la tabla TbCategoria
		TbProveedor prove = em.find(TbProveedor.class, 2);//IdProveedor: Buscar en la tabla TbProveedor
		
		prod.setIdProd("P0021");
		prod.setDesProd("Ketokonazol");
		prod.setStkProd(30);
		prod.setPreProd(new BigDecimal("30.00"));
		prod.setTbCategoria(cate);
		prod.setEstProd((byte) 1);
		prod.setTbProveedor(prove);
		// Realiza la operación de persistencia
	    em.persist(prod);
		
		em.getTransaction().commit();
		System.out.println("==============================================");
		
	}
	
	public static void ActualizarProducto(){
		
		System.out.println("===JPA CRUD====");
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("Propuesto1");
		
		EntityManager em = fabrica.createEntityManager();
		
		
		/*Actualizar usuario*/
		System.out.println("Actualizamos usuario con id=6: y actualizamos campo cla_usua=10001");
		System.out.println("-------------------------------------------------------------------");
		em.getTransaction().begin();
		TbProducto ProductoActualizar = em.find(TbProducto.class,"POO19");
		TbCategoria cate = em.find(TbCategoria.class, 2);//Tipo Cliente: Buscar en la tabla TbCategoria
		TbProveedor prove = em.find(TbProveedor.class, 2);//Tipo Cliente: Buscar en la tabla TbProveedor
		
		
		ProductoActualizar.setDesProd("vickvaporud");
		ProductoActualizar.setStkProd(41);
		ProductoActualizar.setPreProd(new BigDecimal("5.00"));
		ProductoActualizar.setTbCategoria(cate);
		ProductoActualizar.setEstProd((byte) 1);
		ProductoActualizar.setTbProveedor(prove);
		em.persist(ProductoActualizar);
		em.getTransaction().commit();
		System.out.println("==============================================");
		
		
	}
	
	public static void EliminarProducto(String idprod){
		System.out.println("===JPA CRUD====");
		 // Crear una factoría de EntityManagers (EntityManagerFactory)
	    EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("Propuesto1");

	    // Crear un EntityManager para interactuar con la base de datos
	    EntityManager em = fabrica.createEntityManager();

	    try {
	        /* Eliminar producto */
	        System.out.println("===JPA CRUD====");
	        System.out.println("Eliminar Producto con ID: " + idprod);
	        System.out.println("-------------------------");

	        // Iniciar una transacción
	        em.getTransaction().begin();

	        // Buscar el producto por su ID
	        TbProducto ProductoEliminar = em.find(TbProducto.class, idprod);

	        // Verificar si el producto existe antes de eliminarlo
	        if (ProductoEliminar != null) {
	            em.remove(ProductoEliminar); // Eliminar el producto
	            em.getTransaction().commit(); // Confirmar la transacción
	            System.out.println("Producto eliminado con éxito.");
	        } else {
	            System.out.println("El producto con ID " + idprod + " no existe En la Base de Datos.");
	        }

	    } catch (Exception e) {
	        // Manejar excepciones y realizar rollback en caso de error
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        // Cerrar el EntityManager y la EntityManagerFactory para liberar recursos
	        if (em != null) {
	            em.close();
	        }
	        if (fabrica != null) {
	            fabrica.close();
	        }
	    }
		
	}
	
	public static  void ValidarAcceso(String usr,String clave){
		// Crear una factoría de EntityManagers (EntityManagerFactory)
	    EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("Propuesto1");

	    // Crear un EntityManager para interactuar con la base de datos
	    EntityManager em = fabrica.createEntityManager();
	    
		Query query = em.createNativeQuery("{call SP_validarusuario(?,?)}",TbUsuario.class);
		query.setParameter(1,usr);
		query.setParameter(2,clave);
		TbUsuario u =(TbUsuario) query.getSingleResult();
		
		if(u==null){
		           System.out.println("Usuario no existe");
		}else{
		        System.out.println("Bienvenido " + u.getNomUsua());
		       System.out.println("Tus datos son: ");
		       System.out.println("CodUser: " + u.getCodUsua());
		       System.out.println("Nombre " + u.getNomUsua());
		       System.out.println("Apellido: " + u.getApeUsua());	    
		       System.out.println("fechaNac: " + u.getFnaUsua());
		       
		       
		}
		System.out.println("---------------------------------------------------");
	}

}
