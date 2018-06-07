/** see ../../../../../../../LICENSE for release rights */
package ws.nzen.crypto.sodium.ltc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.IllegalBlockingModeException;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/** 
 */
public class Bank
{
	private static final String cl = "b.";
	private ThreadPoolExecutor executor;
	private int tcpPort = 3876;
	private ServerSocket ear;


	public Bank()
	{
		executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
		try
		{
			ear = new ServerSocket( tcpPort );
		}
		catch ( java.io.IOException | SecurityException | java.nio.channels.IllegalBlockingModeException iff )
		{
			System.err.println( "couldn't accept a connection "+ iff );
		}
	}


	public void serveClientele()
	{
		final String here = cl +"e ";
		System.out.println( here +"listening for clients" );
		while ( true )
		{
			Socket bidiChannel;
			try
			{
				bidiChannel = ear.accept();
				System.out.println( here +"caught a client" );
				Dispatchee autoResponder = new Dispatchee();
				autoResponder.setSocket( bidiChannel );
				executor.execute( autoResponder );
			}
			catch ( IOException ie )
			{
				System.err.println( here +"couldn't accept connections"+ ie );
			}
		}
	}


	protected class Dispatchee implements Runnable
	{
		Socket bidiChannel;


		public void run()
		{
			final String here = "b.d.r ";
			try ( PrintWriter netOut = new PrintWriter(
							bidiChannel.getOutputStream(), true );
					BufferedReader netIn = new BufferedReader(
							new InputStreamReader( bidiChannel.getInputStream() ) ); )
			{
				System.out.println( here +"responding a client, concurrently" );
				respond( netIn, netOut );
			}
			catch ( IOException | SecurityException | IllegalBlockingModeException ie )
			{
				System.err.println( here +"couldn't accept connections"+ ie );
			}
			finally
			{
				try
				{
					bidiChannel.close();
				}
				catch ( IOException ie )
				{
					System.err.println( here +"couldn't close sockt"+ ie );
				}
			}
		}


		private void respond( BufferedReader netIn,
				PrintWriter netOut ) throws IOException
		{
			final String here = cl +"r ";
			String inputT, outputT;
			// AhpProtocol referee = new AhpProtocol();
			int times = 0;
			while ( (inputT = netIn.readLine()) != null ) // IMPROVE assignment as expression
			{
				System.out.println( here +"Bank received: "+ inputT );
				outputT = inputT;// referee.replyFrom( inputT );
				JSONObject request = JSON.parseObject( inputT );
				if ( times > 0 )
				{
					break;
				}
				else if ( outputT.equals( "END" ) )
				{
					ear.close();
				}
				else if ( request.containsKey( "cmd" ) )
				{
					String typeOfRequest = request.getString( "cmd" );
					if ( typeOfRequest.equals( "balance" ) )
					{
						request.put( "balance", "0" );
						outputT = JSON.toJSONString( request );
						netOut.println( outputT ); // echo
						times++;
					}
				}
				else
				{
					netOut.println( outputT ); // echo
					times++;
				}
			}
		}


		public void setSocket( Socket earTrumpet )
		{
			bidiChannel = earTrumpet;
		}


	}

}




















