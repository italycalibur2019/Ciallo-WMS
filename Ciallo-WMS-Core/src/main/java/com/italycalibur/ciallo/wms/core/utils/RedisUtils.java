package com.italycalibur.ciallo.wms.core.utils;

import com.italycalibur.ciallo.wms.core.redis.PipelinedOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @description: redis 工具类
 * @author dhr
 * @date 2025-02-25 14:23:19
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class RedisUtils {
    private final RedisTemplate<String, Object> template;

    /**
     * 获取缓存
     * @see #get(String)
     */
    public <T extends Serializable> T find(String key) {
        return this.get(key);
    }

    /**
     * 获取缓存
     * @param key 键
     * @return 键值
     * @param <T> 结果类型
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T get(String key) {
        return (T) this.template.opsForValue().get(key);
    }

    /**
     * 设置缓存新值, 并返回旧值
     * @param key 键
     * @param newValue 新值
     * @return 旧值
     * @param <T> 结果类型
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T getSet(String key, Object newValue) {
        return (T)(this.template.opsForValue().getAndSet(key, newValue));
    }

    /**
     * 判断缓存是否存在
     * @param key 键
     * @return 存在返回 true, 否则返回 false
     */
    public boolean exists(String key) {
        if (key == null) {
            return false;
        }
        //noinspection ConstantConditions
        return this.template.hasKey(key);
    }

    /**
     * 设置缓存
     * @param key 键
     * @param value 值
     */
    public void set(String key, Object value) {
        this.template.opsForValue().set(key, value);
    }

    /**
     * 设置缓存及过期时间
     * @param key 键
     * @param value 值
     * @param duration 过期时间 {@link Duration}
     */
    public void set(String key, Object value, Duration duration) {
        this.template.opsForValue().set(key, value, duration);
    }

    /**
     * 设置缓存及过期时间
     * @param key 键
     * @param value 值
     * @param seconds 过期时间，单位：秒
     */
    public void set(String key, Object value, long seconds) {
        this.set(key, value, Duration.ofSeconds(seconds));
    }

    /**
     * 键值自增，默认自增1
     * @param key 键
     * @return 自增后的值
     */
    public Long increment(String key) {
        return this.template.opsForValue().increment(key);
    }

    /**
     * 键值自增，自定义量
     * @param key 键
     * @param delta 增量
     * @return 自增后的值
     */
    public Long increment(String key, long delta) {
        return this.template.opsForValue().increment(key, delta);
    }

    /**
     * 键值自减，默认自减1
     * @param key 键
     * @return 自减后的值
     */
    public Long decrement(String key) {
        return this.template.opsForValue().decrement(key);
    }

    /**
     * 键值自增，自定义量
     * @param key 键
     * @param delta 减量
     * @return 自减后的值
     */
    public Long decrement(String key, long delta) {
        return this.template.opsForValue().decrement(key, delta);
    }

    /**
     * 删除缓存
     * @param key 键
     */
    public void remove(String key) {
        this.template.delete(key);
    }

    /**
     * 批量删除缓存
     * @param keys 键集合
     */
    public void remove(Collection<String> keys) {
        this.template.delete(keys);
    }

    /**
     * 设置过期时间
     * @param key 键
     * @param duration 过期时间 {@link Duration}
     */
    public void expire(String key, Duration duration) {
        this.template.expire(key, duration);
    }

    /**
     * 设置过期时间到
     * @param key 键
     * @param time 日期
     */
    public void expire(String key, Date time) {
        this.template.expireAt(key, time);
    }

    /**
     * 重命名缓存
     * @param oldKey 旧键名
     * @param newKey 新键名
     */
    public void rename(String oldKey, String newKey) {
        this.template.rename(oldKey, newKey);
    }

    /**
     * 获取缓存集合
     * @param keys 键集合
     * @return 值集合
     * @param <T> 结果类型
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    public <T extends Serializable> List<T> multiGet(Collection<String> keys) {
        return (List) this.template.opsForValue().multiGet(keys);
    }

    /**
     * 批量添加缓存
     * @param multiData 键值对集合
     * @param <T> 结果类型
     */
    public <T extends Serializable> void multiSet(Map<String, ? extends T> multiData) {
        this.template.opsForValue().multiSet(multiData);
    }

    /**
     * 通过 {@link PipelinedOperations} 批量操作
     * @param multiSet 操作集合
     * @return 批量操作返回结果
     */
    @SuppressWarnings("unchecked")
    public List<Object> multiOpsValue(PipelinedOperations<ValueOperations<String, Object>> multiSet) {
        return this.template.executePipelined(new SessionCallback<>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                multiSet.operate((ValueOperations<String, Object>) operations.opsForValue());
                // 返回null即可，因为返回值会被管道的返回值覆盖，外层取不到这里的返回值
                return null;
            }
        });
    }

    /**
     * 设置或清除缓存指定偏移量上的位
     * @param key 键
     * @param offset 偏移量
     * @param value 值
     * @return
     */
    public Boolean setBit(String key, long offset, boolean value) {
        return this.template.opsForValue().setBit(key, offset, value);
    }

    /**
     * 获取缓存指定偏移量上的位
     * @param key 键
     * @param offset 偏移量
     * @return 值
     */
    public Boolean getBit(String key, long offset) {
        return this.template.opsForValue().getBit(key, offset);
    }

    /**
     * 发布主题消息
     * @param channel 主题/通道
     * @param message 消息体
     */
    public void publish(String channel, Object message) {
        this.template.convertAndSend(channel, message);
    }

    /**
     * 从消息体获取最终数据对象
     * @param message 接收到的消息
     * @param <T> 结果类型
     * @return 结果数据
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T receive(byte[] message) {
        return (T) this.template.getValueSerializer().deserialize(message);
    }

    /**
     * 从指定偏移量开始，覆盖键的部分，并以给定值
     * @param key 键
     * @param offset 偏移量
     * @param value 值
     */
    public void setRange(String key, long offset, String value) {
        this.template.opsForValue().set(key, value, offset);
    }

    /**
     * 将字符串值附加到键值中
     * @param key 键
     * @param value 附加字符串
     * @return 附加后值的长度
     */
    public Integer append(String key, String value) {
        return this.template.opsForValue().append(key, value);
    }

    /**
     * 往集合头部加数据
     * @param key   键
     * @param value 值
     * @return 集合长度
     */
    public Long leftPush(String key, Object value) {
        return this.template.opsForList().leftPush(key, value);
    }

    /**
     * 往集合尾部加数据
     * @param key   键
     * @param value 值
     * @return 集合长度
     */
    public Long rightPush(String key, Object value) {
        return this.template.opsForList().rightPush(key, value);
    }

    /**
     * 从集合头部取出数据
     * @param key 键
     * @return 取出的值
     * @param <T> 结果类型
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T leftPop(String key) {
        return (T) this.template.opsForList().leftPop(key);
    }

    /**
     * 在指定时间内，从集合头部取出数据
     * @param key 键
     * @param timeout 超时时间
     * @return 取出的值
     * @param <T> 结果类型
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T leftPop(String key, Duration timeout) {
        return (T) this.template.opsForList().leftPop(key, timeout);
    }

    /**
     * 从集合尾部取出数据
     * @param key 键
     * @return 取出的值
     * @param <T> 结果类型
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T rightPop(String key) {
        return (T) this.template.opsForList().rightPop(key);
    }

    /**
     * 在指定时间内，从集合尾部取出数据
     * @param key 键
     * @param timeout 超时时间
     * @return 取出的值
     * @param <T> 结果类型
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T rightPop(String key, Duration timeout) {
        return (T) this.template.opsForList().rightPop(key, timeout);
    }


    /**
     * 集合中首次出现指定值的索引
     * @param key 键
     * @param value 值
     * @return 索引
     */
    public Long indexOf(String key, Object value) {
        return this.template.opsForList().indexOf(key, value);
    }

    /**
     * 集合中最后一次出现指定值的索引
     * @param key 键
     * @param value 值
     * @return 索引
     */
    public Long lastIndexOf(String key, Object value) {
        return this.template.opsForList().lastIndexOf(key, value);
    }

    /**
     * 集合长度
     * @param key 键
     * @return 长度
     */
    public Long size(String key) {
        return this.template.opsForList().size(key);
    }

    /**
     * 在指定索引上设置值
     * @param key 键
     * @param index 索引
     * @param value 值
     */
    public void setAt(String key, long index, Object value) {
        this.template.opsForList().set(key, index, value);
    }

    /**
     * 获取指定索引的值
     * @param key 键
     * @param index 索引
     * @param <T> 结果类型
     * @return 值
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T getAt(String key, long index) {
        return (T) this.template.opsForList().index(key, index);
    }

    /**
     * 根据给定的模式匹配并返回Redis中所有符合条件的键
     * @param pattern 给定的模式
     * @return 键集合
     */
    public Set<String> keys(String pattern) {
        return this.template.keys(pattern);
    }

    /**
     * 通过scan命令获取符合模式的键，默认返回String类型
     * @param pattern 给定的模式
     * @return 键集合
     */
    public Set<String> scan(String pattern) {
        return this.scan(pattern, DataType.STRING);
    }

    /**
     * 通过scan命令获取符合模式的键
     * @param pattern 给定的模式
     * @param dataType 数据类型： {@link DataType}
     * @return keys
     */
    @SuppressWarnings({ "Convert2Lambda" })
    public Set<String> scan(String pattern, DataType dataType) {
        return this.template.execute(new RedisCallback<>() {
            @Override
            public Set<String> doInRedis(RedisConnection connection) throws DataAccessException {
                /*注意:scan命令中count是redis每次进行scan时扫描的数量，不是结果集数量. 一般使用Int.MAX_VALUE,根据实际情况这里使用10_000*/
                Set<String> result;
                try (Cursor<byte[]> keys = connection.keyCommands()
                        .scan(KeyScanOptions.scanOptions(dataType == null ? DataType.STRING : dataType).count(10_000).match(pattern).build())) {
                    result = new LinkedHashSet<>();
                    keys.forEachRemaining(b -> result.add(new String(b, StandardCharsets.UTF_8)));
                }
                return result;
            }
        });
    }

    /**
     * 获取键的剩余过期时间
     * @param key 键
     * @return 过期时间，单位秒
     */
    public Long getExpire(String key) {
        return this.template.getExpire(key);
    }

    /**
     * 获取指定单位的键的剩余过期时间
     * @param key 键
     * @param timeUnit 时间单位: {@link TimeUnit}
     * @return 过期时间
     */
    public Long getExpire(String key, TimeUnit timeUnit) {
        return this.template.getExpire(key, timeUnit);
    }
}
