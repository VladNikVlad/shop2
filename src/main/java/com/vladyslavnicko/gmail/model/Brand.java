package com.vladyslavnicko.gmail.model;


import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "brand")
@Data
public class Brand {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "name")
	String name;
	
	@OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
	private List<Product> products;
	
	@ManyToOne
    @JoinColumn(name = "creator_user_id", referencedColumnName = "id", nullable = false)
    private User creatorUser;
    
    @Column(name = "create_date", nullable = false)
    private Date createDate;
    
    @Column(name = "edit_date")
    private Date editDate;
    
    @Column(name = "delete_date")
    private Date deeleteDate;


}
