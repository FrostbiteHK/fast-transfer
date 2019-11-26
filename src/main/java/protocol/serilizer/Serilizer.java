package protocol.serilizer;

import protocol.serilizer.impl.JSONSerlizer;

/**
 * @author J95ha
 * @title: Serilizer
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 12:58
 */
public interface Serilizer {

	Serilizer DEFAULT = new JSONSerlizer();

	byte[] serilize(Object object);

	<T> T deSerilize(byte[] bytes, Class<T> clazz);
}
