/**
 * 
 */
package server;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author qty
 *
 */
public interface IHello extends Remote {
	/**
	  * 在服务器打印客户端发过来的message 并返回xml字符串
	  */
     public String sayHello(String message) throws RemoteException; 
     
     /**
      * 向服务器发送给xml文件
      * @param file
      * @return
      * @throws RemoteException
      */
     public String sendXml(String base64Message)throws RemoteException;
}
