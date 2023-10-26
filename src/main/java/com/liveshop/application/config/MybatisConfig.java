package com.liveshop.application.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * @Configuration : 설정파일을 CGLIB 방식을 통하여 바이트단위로 싱글톤을 보장하며
 * Bean을 ApplicationConfigApplicationContext에 주입한다.
 */
//@Configuration
/**
 * Mapper Scan : 매퍼를 하나씩 등록하는게 아닌 패키지 경로를 지정하여
 * 이하 위치에 있는 인터페이스들은 전부 매퍼로 사용할 수 있게 해줌.
 * 이는 3.0 이전 버전에서 지원하던 내용들임. 현재는 @Mapper 로 대체할 수 있음.
 */
//@MapperScan("com.liveshop.application.mapper")
public class MybatisConfig {

    //@Autowired
    private ApplicationContext applicationContext;

    /**
     * DataSource : DB와 관련된 커넥션 정보를 담고 있음
     * sqlSessionFactoryBean : Spring의 FactoryBean 인터페이스 구현체이며 여기서 FactoryBean은 SqlSessionFactory을 제네릭으로 사용하고 있습니다.
     */
//    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/*8/*.xml"));

        return sqlSessionFactoryBean.getObject(); // getObject는 XML와 datasource를 넣은 Facotry를 Build 해서 반환함.
    }

    /**
     * SqlSessionTemplate는 SqlSession을 구현하고 코드에서 SqlSession을 대체하는 역할을 함.
     * 쓰레드에 안전하고 여러개의 DAO나 매퍼에서 공유할 수 있음.
     * SqlSession이 현재의 스프링 트랜잭션(롤백, 커밋)에서 사용될수 있도록 보장함.
     */
//    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory); //
    }
}
