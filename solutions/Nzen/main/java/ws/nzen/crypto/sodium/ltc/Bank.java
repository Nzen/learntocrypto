/** see ../../../../../../../LICENSE for release rights */
package ws.nzen.crypto.sodium.ltc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.IllegalBlockingModeException;

/** 
 */
public class Bank
{
	private static final String cl = "b.";
	private int tcpPort = 3876;
	private ServerSocket ear;

	/**
	 * @param args
	 */
	public static void main( String[] args )
	{
		// cli stuff, eventually
	}


	public Bank()
	{
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
		try ( Socket bidiChannel = ear.accept();
				PrintWriter stdOut = new PrintWriter(
						bidiChannel.getOutputStream(), true );
				BufferedReader stdIn = new BufferedReader(
						new InputStreamReader( bidiChannel.getInputStream() ) ); )
		{
			respond( stdIn, stdOut );
		}
		catch ( IOException | SecurityException | IllegalBlockingModeException ie )
		{
			System.err.println( here +"couldn't accept connections"+ ie );
		}
	}


	private void respond( BufferedReader stdIn,
			PrintWriter stdOut ) throws IOException
	{
		String inputT, outputT;
		// AhpProtocol referee = new AhpProtocol();
		while ( (inputT = stdIn.readLine()) != null ) // IMPROVE assignment as expression
		{
			outputT = "Bank received:"+ inputT;// referee.replyFrom( inputT );
			if ( outputT.equals( "END" ) )
			{
				break;
			}
		}
	}

}




















