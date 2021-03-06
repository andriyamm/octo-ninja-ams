package org.amm.ams.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@NamedQueries({
	@NamedQuery(
		name="findByPrefix",
		query="from Language lang where lang.prefix = :prefix "),
})
@Entity
@XmlRootElement(name = "language")
public class Language implements Serializable {

	private static final long serialVersionUID = -2678498818708026361L;

	@Id
	@GeneratedValue
	@Column(name = "language_id")
	private Long id;

	private String name;

	@Column(unique = true, nullable = false)
	private String prefix;

	@OneToMany(mappedBy = "language")
	private Set<CategoryDef> categoryDef = new HashSet<CategoryDef>();

	@OneToMany(mappedBy = "articleLang")
	private Set<ArticleDef> articleDef = new HashSet<ArticleDef>();

	public Language() {
		super();
	}

	public Language(Long id, String name, String prefix) {
		super();
		this.id = id;
		this.name = name;
		this.prefix = prefix;
	}

	@XmlAttribute
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@JsonIgnore
	@XmlTransient
	public Set<CategoryDef> getCategoriesDef() {
		return categoryDef;
	}

	public void setCategoriesDef(Set<CategoryDef> categoriesDef) {
		this.categoryDef = categoriesDef;
	}

	@JsonIgnore
	@XmlTransient
	public Set<ArticleDef> getArticlesDef() {
		return articleDef;
	}

	public void setArticlesDef(Set<ArticleDef> articlesDef) {
		this.articleDef = articlesDef;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(id).append(name)
				//.append(categoryDef)
				//.append(articleDef)
				.append(prefix)
				.toHashCode();
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

		Language rhs = (Language) obj;
		return new EqualsBuilder().append(id, rhs.id)
				.append(name, rhs.name)
				//.append(articleDef, rhs.articleDef)
				//.append(categoryDef, rhs.categoryDef)
				.append(prefix, rhs.prefix).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id)
				.append("name", name)
				//.append("articleDef", articleDef)
				//.append("categoryDef", categoryDef)
				.append("prefix", prefix).toString();
	}
}
