package com.insta.instagram.modal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.insta.instagram.dto.UserDto;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String Caption;
	
	private String image;
	private String location;
	private LocalDateTime createdAt;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="id",column=@Column(name="user_id")),
		@AttributeOverride(name="email",column=@Column(name="user_email"))
	})
	private UserDto user;
	
	@OneToMany
	private List<Comments> comments = new ArrayList<Comments>();
	
	@Embedded
	@ElementCollection
	@JoinTable(name="likedbyUsers",joinColumns = @JoinColumn(name="user_id"))
	private Set<UserDto> likedbyUsers = new HashSet<>();
	
	
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Post(Integer id, String caption, String image, String location, LocalDateTime createdAt, UserDto user,
			List<Comments> comments, Set<UserDto> likedbyUsers) {
		super();
		this.id = id;
		Caption = caption;
		this.image = image;
		this.location = location;
		this.createdAt = createdAt;
		this.user = user;
		this.comments = comments;
		this.likedbyUsers = likedbyUsers;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCaption() {
		return Caption;
	}
	public void setCaption(String caption) {
		Caption = caption;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public List<Comments> getComments() {
		return comments;
	}
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	public Set<UserDto> getLikedbyUsers() {
		return likedbyUsers;
	}
	public void setLikedbyUsers(Set<UserDto> likedbyUsers) {
		this.likedbyUsers = likedbyUsers;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", Caption=" + Caption + ", image=" + image + ", location=" + location
				+ ", createdAt=" + createdAt + ", user=" + user + ", comments=" + comments + ", likedbyUsers="
				+ likedbyUsers + "]";
	}
	
	
}
