package com.axiante.utility;

import java.util.Base64;
import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;

public class SPNEGOUtilities {

	public String getToken(String userName, String domain, String server) throws GSSException {
		System.setProperty("javax.security.auth.useSubjectCredsOnly", "false");
		Oid krb5MechOid = new Oid("1.2.840.113554.1.2.2");
		Oid spnegoMechOid = new Oid("1.3.6.1.5.5.2");

		GSSManager manager = GSSManager.getInstance();
		GSSName gssUserName = manager.createName(domain+"\\"+userName, GSSName.NT_USER_NAME, krb5MechOid);
		GSSCredential clientGssCreds = manager.createCredential(gssUserName.canonicalize(krb5MechOid),
				GSSCredential.INDEFINITE_LIFETIME, krb5MechOid, GSSCredential.INITIATE_ONLY);
		clientGssCreds.add(gssUserName, GSSCredential.INDEFINITE_LIFETIME, GSSCredential.INDEFINITE_LIFETIME,
				spnegoMechOid, GSSCredential.INITIATE_ONLY);

		// create target server SPN
		GSSName gssServerName = manager.createName(server, GSSName.NT_USER_NAME);

		GSSContext clientContext = manager.createContext(gssServerName.canonicalize(spnegoMechOid), spnegoMechOid,
				clientGssCreds, GSSContext.DEFAULT_LIFETIME);
		// optional enable GSS credential delegation
		clientContext.requestCredDeleg(true);

		byte[] spnegoToken = new byte[0];

		// create a SPNEGO token for the target server
		spnegoToken = clientContext.initSecContext(spnegoToken, 0, spnegoToken.length);
		return Base64.getEncoder().encodeToString(spnegoToken);
	}
}
