package group.nel.ipfs.lib;

import java.io.IOException;
import java.util.List;

import io.ipfs.api.IPFS;
import io.ipfs.api.KeyInfo;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multiaddr.MultiAddress;
import io.ipfs.multihash.Multihash;

public class IPFSClient {
	private final String DEFAULT_CHARSET = "utf-8";
	private IPFS ipfs = new IPFS(new MultiAddress("/ip4/127.0.0.1/tcp/5001"));
	
	public IPFSClient(String url) {
		if(url != null && url.trim().length() > 0) {
			ipfs = new IPFS(new MultiAddress(url));
		}
	}
	
	public String addContent(String content) {
		String res = null;
		try {
			List<MerkleNode>  list = ipfs.add(new NamedStreamable.ByteArrayWrapper(content.getBytes()));
			res = list.get(0).hash.toBase58();
		} catch (IOException e) {
			res = "err:(add) " + e.getMessage();
		}
		return res;
	}
	
	public String getContent(String hash) {
		String res = null;
		try {
			byte[] get = ipfs.cat(Multihash.fromBase58(hash));
			res = new String(get, DEFAULT_CHARSET);
		} catch (IOException e) {
			res = "err:(get) " + e.getMessage();
		}
		return res;
	}
	
	public String keys() {
		try {
			List<KeyInfo> keyInfos = ipfs.key.list();
			keyInfos.forEach(p -> {
				
				System.out.println(String.format("name:%s, id.58:%s, id.hx:%s, id.st:%s, type:%s",
						p.name,
						p.id.toBase58(),
						p.id.toHex(),
						p.id.toString(),
						p.id.type
						));
			});
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "true";
	}
}
