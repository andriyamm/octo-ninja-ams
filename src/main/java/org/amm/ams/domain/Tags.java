package org.amm.ams.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class Tags implements Serializable, Identifiable {

	private static final long serialVersionUID = 3110082861251118641L;

	@Id
	@GeneratedValue
	@Column(name = "tags_id")
	private Long id;

	private String tags;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
	private Set<Articles> articles = new HashSet<Articles>();

	protected Tags() {

	}

	public Tags(Long tags_id, String tags, Set<Articles> articles) {
		this.id = tags_id;
		this.tags = tags;
		this.articles = articles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long tags_id) {
		this.id = tags_id;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Set<Articles> getArticles() {
		return articles;
	}

	public void setArticles(Set<Articles> articles) {
		this.articles = articles;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(id).append(tags)
				.append(articles).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		Tags rhs = (Tags) obj;
		return new EqualsBuilder().append(id, rhs.id).append(tags, rhs.tags)
				.append(articles, rhs.articles).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("tags", tags)
				.append("articles", articles).toString();
	}

}
