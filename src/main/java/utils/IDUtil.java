package utils;

import java.util.UUID;

/**
 * @author J95ha
 * @title: IDUtil
 * @projectName fast-transfer
 * @description: TODO
 * @date 2019/11/26 14:03
 */
public class IDUtil {

	/*
UUID是javaJDK提供的一个自动生成主键的方法。UUID(Universally Unique Identifier)全局唯一标识符,是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的，
是由一个十六位的数字组成,表现出来的形式。由以下几部分的组合：当前日期和时间(UUID的第一个部分与时间有关，如果你在生成一个UUID之后，过几秒又生成一个UUID，
则第一个部分不同，其余相同)，时钟序列，全局唯一的IEEE机器识别号（如果有网卡，从网卡获得，没有网卡以其他方式获得），UUID的唯一缺陷在于生成的结果串会比较长。
 */
	public static String randomId() {
		return UUID.randomUUID().toString().split("-")[0];
	}
}
