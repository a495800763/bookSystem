package com.liumq.booksystem.entity;

import javax.persistence.*;

/**
 * 网站配置实体
 */
@Entity
@Table(name="config")
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    /**
     * website Name
     */
    private String webName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }
}
