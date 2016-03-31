<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:include href="../../global_util.xsl" />
	<xsl:include href="../includes/blog_variables.xsl" />
	<xsl:include href="../includes/blog_nav.xsl" />

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>
	<xsl:template match="data">
		<xsl:apply-templates select="blog" />
	</xsl:template>
	<xsl:template match="blog">
		<form action="" method="post" name="portal_form">
			<input type="hidden" name="COMPONENT_ID" value="{/data/environment/componentId}" />
			<input type="hidden" name="ACTION" />
			<input type="hidden" name="SCREEN" value="ARTICLE" />
			<input type="hidden" name="BLOG_ID" value="{id}" />
			<input type="hidden" name="TOPIC_ID" value="{topic/id}" />
			<!-- survey content -->
			<nav>
				<xsl:call-template name="event_content_navigation">
					<xsl:with-param name="SCREEN" select="'ARTICLE'" />
				</xsl:call-template>
			</nav>
			<div class="col-lg-12 bordered-area">
				<div class="form-group" id="top-actions">
					<div class="btn-toolbar">
						<button class="btn btn-default" onclick="saveTopic();submitForm();">Save</button>
						<button class="btn btn-default" onclick="topics();submitForm();">Back to Topics</button>
						<a class="btn btn-default" href="{$viewUrl}" target="_blank">View Blog</a>
						<a class="btn btn-default" href="{$detailViewUrl}" target="_blank">View Topic</a>
					</div>
				</div>
				<h2>Edit Topic</h2>
				<xsl:call-template name="messages" />
				<xsl:call-template name="main" />
				<div class="form-row">
					<div class="btn-toolbar">
						<button class="btn btn-default" onclick="saveTopic();submitForm();">Save</button>
						<button class="btn btn-default" onclick="topics();submitForm();">Back to Topics</button>
						<a class="btn btn-default" href="{$viewUrl}" target="_blank">View Blog</a>
						<a class="btn btn-default" href="{$detailViewUrl}" target="_blank">View Topic</a>
					</div>
				</div>
			</div>
		</form>
	</xsl:template>
	<xsl:template name="main">
		<xsl:apply-templates select="topic" />
	</xsl:template>
	<xsl:template match="topic">
		<div class="form-group">
			<label for="TOPIC_ARTICLE">Article - <xsl:value-of select="count(file[type='embedded'])" /></label>
			<p class="help-block">Enter the full article here.</p>
			<input type="hidden" name="TOPIC_ARTICLE" id="TOPIC_ARTICLE" value="{article}" />
			<textarea name="TOPIC_ARTICLE">abc</textarea>
			<script>
				tinymce.init({
  selector: 'textarea',
  height: 500,
  plugins: [
    'advlist autolink lists link image charmap print preview anchor',
    'searchreplace visualblocks code fullscreen',
    'insertdatetime media table contextmenu paste code'
  ],
  toolbar: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image embedded_images',
	setup: function(editor) {
    editor.addButton('embedded_images', {
      type: 'menubutton',
      text: 'Embedded Images',
      icon: false,
      menu: [
				<xsl:for-each select="file[type='embedded']">
					<xsl:variable name="fileSrc" src="concat('/uploads/blog/', /data/blog/id, '/', /data/blog/topic/id, '/', name)" />
					{
            classes: 'embedded-image-item',
						image: '<xsl:value-of select="$fileSrc" />',
						onclick: function()
						{
								editor.execCommand('mceInsertContent', false, '<embedimage id="embed-grouping-83086"><div class="mceNonEditable bp-embedded-image  bp-embedded-image-thumb bp-image-left"><figure><a href="https://test.webservices.illinois.edu/blog/files/6300/125327/83086.jpg"><img src="https://test.webservices.illinois.edu/blog/files/6300/125327/83086.jpg"></a></figure><figcaption></figcaption>' + getEditHtml() + '</div></embedimage>');

						}
					},
				</xsl:for-each>
				{
	        text: 'Menu item 1',
	        onclick: function() {
	          editor.insertContent('<strong>Menu item 1 here!</strong>');
	        }
	      },
				{
	        text: 'Menu item 2',
	        onclick: function() {
	          editor.insertContent('<em>Menu item 2 here!</em>');
	        }
	      }
			]
    });
  },
	content_css: [
    '//fast.fonts.net/cssapi/e6dc9b99-64fe-4292-ad98-6974f93cd2a2.css',
    '//www.tinymce.com/css/codepen.min.css'
  ]
});
			</script>
		</div>
	</xsl:template>
</xsl:stylesheet>
