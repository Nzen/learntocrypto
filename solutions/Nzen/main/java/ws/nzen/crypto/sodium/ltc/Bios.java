/** see ../../../../../../../LICENSE for release rights */
package ws.nzen.crypto.sodium.ltc;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/** 
 */
public class Bios
{
	private static final String cl = "pf.";
	static final String helpFlag = "h", roleFlag = "r", depositFlag = "d";
	static final String role_bank = "Bank", role_teller = "Client";


	public static void main( String[] args )
	{
		CommandLine userInput = prepCli( prepCliParser(), args );
		prepDoer( userInput );
	}


	/** fills options with our cli flags and text */
	public static Options prepCliParser()
	{
		Options knowsCliDtd = new Options();
		final boolean needsEmbelishment = true;
		knowsCliDtd.addOption( roleFlag, needsEmbelishment,
				"pretend to be bank or client (teller)" );
		knowsCliDtd.addOption( depositFlag, needsEmbelishment,
				"how much to deposit" );
		knowsCliDtd.addOption( helpFlag, "show arg flags" );
		return knowsCliDtd;
	}


	/** Parses the actual input and shows help, if requested */
	public static CommandLine prepCli(
			Options knowsCliDtd, String[] args )
	{
		CommandLineParser cliRegex = new DefaultParser();
		CommandLine userInput = null;
		try
		{
			userInput = cliRegex.parse( knowsCliDtd, args );
			if ( userInput.hasOption( helpFlag ) )
			{
				new HelpFormatter().printHelp( "LearnToCrypto-Bios", knowsCliDtd );
			}
		}
		catch ( ParseException pe )
		{
			System.err.println( cl +"pc just using config: couldn't parse input "+ pe );
		}
		return userInput;
	}


	public static Bios prepDoer( CommandLine userInput )
	{
		Bios doesStuff = new Bios();
		doesStuff = new Bios();
		if ( userInput != null )
		{
			final String roleToPlay = userInput.getOptionValue( roleFlag, role_bank );
			if ( roleToPlay.equals( role_bank ) )
			{
				new Bank().serveClientele();
			}
			else if ( roleToPlay.equals( role_teller ) )
			{
				String depositAmount = userInput.getOptionValue( depositFlag, "-1" );
				try
				{
					int depositValue = Integer.parseInt( depositAmount );
					if ( depositValue >= 0 )
					{
						new Teller().request( RequestType.DEPOSIT, depositValue ); 
					}
					else
					{
						new Teller().request( RequestType.BALANCE_AMOUNT, -1); 
					}
				}
				catch ( NumberFormatException nfe )
				{
					System.err.println( cl +"pd "+ depositAmount +" is not a valid integer  "+ nfe );
					new Teller().request( RequestType.BALANCE_AMOUNT, -1);
				}
			}
		}
		return doesStuff;
	}


	public Bios()
	{
	}

}




















