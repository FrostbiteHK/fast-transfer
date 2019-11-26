package protocol.serilizer.impl;

import com.alibaba.fastjson.JSON;
import protocol.serilizer.Serilizer;

/**
 * @author J95ha
 * @title: JSONSerlizer
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 13:01
 */
public class JSONSerlizer implements Serilizer {

	/**
	 * 序列化，java对象转换成二进制，使用阿里巴巴的 fastjson 作为序列化框架
	 * @param object
	 * @return
	 */
	public byte[] serilize(Object object) {
		return JSON.toJSONBytes(object);
	}

	/**
	 * 反序列化，二进制转换成java对象
	 * @param bytes
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public <T> T deSerilize(byte[] bytes, Class<T> clazz) {
		return JSON.parseObject(bytes, clazz);
	}
}
