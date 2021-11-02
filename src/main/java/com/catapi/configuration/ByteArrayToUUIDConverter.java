package com.catapi.configuration;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * @author rohit.godara
 *
 */
@ReadingConverter
public class ByteArrayToUUIDConverter implements Converter<byte[], UUID> {
	@Override
	public UUID convert(byte[] source) {
		ByteBuffer bb = ByteBuffer.wrap(source);
		return new UUID(bb.getLong(), bb.getLong());
	}
}
