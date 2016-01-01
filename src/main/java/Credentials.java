import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by hagai_lvi on 01/01/2016.
 */
public class Credentials {

	Configuration configuration;
	public Credentials() throws ConfigurationException {
		this.configuration = new PropertiesConfiguration("credentials.properties");
	}

	public String getCredentials(){
		return configuration.getString("TEXTRAZOR_API_KEY");
	}
}
