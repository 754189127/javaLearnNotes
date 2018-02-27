mock写法

@Bean
public RedisConnectionFactory connectionFactory() {
    try {
        JedisConnectionFactory cf = new JedisConnectionFactory();
        cf.setHostName("localhost");
        cf.setPort(6379);
        cf.afterPropertiesSet();
        return cf;
    }
    catch (RedisConnectionFailureException e) {
        RedisConnectionFactory mockCF = mock(RedisConnectionFactory.class);
        when(mockCF.getConnection()).thenReturn(mock(RedisConnection.class));
        return mockCF;
    }
}

