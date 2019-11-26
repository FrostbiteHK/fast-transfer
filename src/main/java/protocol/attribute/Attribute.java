package protocol.attribute;

import io.netty.util.AttributeKey;
import protocol.session.Session;

/**
 * @author J95ha
 * @title: Attribute
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 14:02
 */
public interface Attribute {

	AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
