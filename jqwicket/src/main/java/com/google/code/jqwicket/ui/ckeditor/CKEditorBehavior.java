package com.google.code.jqwicket.ui.ckeditor;

import java.util.UUID;


import org.apache.wicket.RequestCycle;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;

import com.google.code.jqwicket.ui.JQComponentBehaivor;

/**
 * 
 * @author mkalina
 */
public class CKEditorBehavior extends JQComponentBehaivor<CKEditorOptions>
		implements ICKEditor, IHeaderContributor {

	private static final long serialVersionUID = 1L;

	private static final ResourceReference baseRef = new ResourceReference(
			CKEditorOptions.class, "");

	public CKEditorBehavior() {
		this(new CKEditorOptions());
	}

	public CKEditorBehavior(CKEditorOptions options) {
		super(options);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.google.code.jqwicket.IJQWidget#getName()
	 */
	public CharSequence getName() {
		return JQ_COMPONENT_NAME;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.apache.wicket.behavior.AbstractBehavior#renderHead(org.apache.wicket.markup.html.IHeaderResponse)
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
		CharSequence baseUrl = RequestCycle.get().urlFor(baseRef);
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("var CKEDITOR_BASEPATH = '%s';\n", baseUrl));
		buf.append(String
				.format("function CKEDITOR_GETURL(resource){\n"
						+ " return resource.indexOf('%s') >= 0 ? resource : '%s' + resource;\n"
						+ "}", baseUrl, baseUrl));
		response.renderJavascript(buf, UUID.randomUUID().toString());
	}

}