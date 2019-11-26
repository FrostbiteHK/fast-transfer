package protocol.session;

import lombok.Data;

/**
 * @author J95ha
 * @title: Session
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 12:56
 */

@Data
public class Session {

	private String nodeId;

	private String nodeName;


	public Session() {
	}

	public Session(String nodeId, String nodeName) {
		this.nodeId = nodeId;
		this.nodeName = nodeName;
	}
}
