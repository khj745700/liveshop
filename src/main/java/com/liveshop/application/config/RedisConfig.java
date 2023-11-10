package com.liveshop.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Spring의 autoConfiguration이 yml파일에 설정된 property로 불러올 수 있으나, 코드상으로 명시적 표현을 위해 직접 작성하였습니다.
 * <h2><code style="font-size:15">@EnableRedisHttpSession</code></h2>
 * <ul>
 *     <li>Spring 애플리케이션이 Redis가 지원하는 분산 세션 (distributed sessions backed by redis)을 사용할 수 있게 해줌.</li>
 *     <li>애플리케이션 메모리 대신 Redis 메모리를 사용하게 되므로 애플리케이션의 수평적 확장에 유용하고 모든 인스턴스에서 세션 데이터가 일관되게 유지됨.</li>
 *     <li>스프링 부트에서는 <a href="https://velog.io/@dailyzett/Spring-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%EC%97%90-redis-%EC%97%B0%EB%8F%99">springSessionRepositoryFilter</a> Bean 등록을 할 필요는 없음</li>
 * </ul>
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800) // 세션 유지 기간 설정, default = 30분 = 1800초
public class RedisConfig {

    /**
     * <h2>Lettuce vs Jedis ?</h2>
     * <p>
     *     Lettuce와 Jedis는 모두 유명한 Redis Client 오픈 소스인데, 현재 Spring 에서는 Default로 Lettuce를 사용하고 있음.
     * </p>
     * <p>
     *     Jedis의 경우, 멀티 쓰레드 환경에서 하나의 Jedis 인스턴스를 공유하고 싶을 때, 쓰레드 안전성을 보장하지 않음. 멀티 스레드 환경에서 Polling 연결 방식을 사용함.
     *     Jedis를 사용하는 각 동시성을 지닌 스레드는 Jedisrㅏ 상호 작용하는 동안 자체 Jedis 인스턴스를 가져옴. 이는 Redis 연결이 증가하여 Jedis 인스턴스가 늘어날 때마다
     *     물리적인 연결 비용이 발생함.
     * </p>
     * <p>
     *     Lettuce는 netty 기반으로 멀티 스레드 환경에서 상태를 가지고 공유될 수 있음. 따라서 멀티 쓰레드 어플리케이션이 Letuce와 상호 작용하는 동시성을 가진 쓰레드의 개수와
     *     상관없이 하나의 연결만을 사용하면됨.
     *     대신, <a href='https://docs.google.com/presentation/d/1FtEFBCubpcqMJ6C7YV55KAxjhZ5znYn6A3f1c341Lcg/edit#slide=id.p'>ClusterTopology</a>를 설정해야 함.
     *
     * </p>
     */

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }
}

