package me.sise.sidecar.shorturl.repository.entity;

import lombok.Data;
import me.sise.sidecar.shorturl.repository.entity.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "SHORT_URL")
public class ShortUrl extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -8295688402595799135L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String path;
    private String webUrl;
    private Boolean isActive;
}
