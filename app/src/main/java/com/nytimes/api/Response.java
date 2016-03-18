package com.candyspace.nytimes.api;

import java.util.List;

public class Response {

	private String status;
	private List<Article> results;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Article> getResults() {
		return results;
	}

	public void setResults(List<Article> results) {
		this.results = results;
	}

}
