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
@Table(name = "ROUTE_LOG")
public class RouteLog extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private ShortUrl shortUrl;
    private String referer;
    private String userAgent;
    private String remoteAddress;
}
