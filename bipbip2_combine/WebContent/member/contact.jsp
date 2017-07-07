<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<div class="row" style="margin: 1%;">
	<div class="col-md-6" style="padding-top: 4%;">
		<h2 class="tm-contact-info">Say hello to us!</h2>
		<p class="tm-text">저희 사이트에 관하여 문의사항이 있으시면 다음주소로 메일 보내주시면 감사하겠습니다.<br>
		<h4>hardkyo88@gmail.com<h4></h4>  이곳으로 문의해 주시기 바랍니다.</p>

		<!-- contact form -->
		<form action="/bipbip1/member/mailSend.jsp" method="post">

			<div class="form-group">
				<input type="email" id="to" name="to"
					class="form-control" placeholder="받는사람" required  value="hardkyo88@gmail.com"/>
			</div>

			<div class="form-group">
				<input type="email" id="from" name="from"
					class="form-control" placeholder="보내는사람" required value="jks88ds@naver.com"/>
			</div>

			<div class="form-group">
				<input type="text" id="subject" name="subject"
					class="form-control" placeholder="Subject" required />
			</div>

			<div class="form-group">
				<textarea id="content" name="content"
					class="form-control" rows="5" placeholder="Your message" required></textarea>
			</div>

			<button type="submit" class="btn btn-primary">Send</button>
		</form>
	</div>


	<div class="col-md-6" style="padding-top: 4%;">
		<div>
			<h2>문의사항</h2>
			<div id="disqus_thread"></div>
			<script>
				/**
				 *  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
				 *  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables*/

				var disqus_config = function() {
					this.page.url = 'http://example.com/bipbip1-to-page.html'; // Replace PAGE_URL with your page's canonical URL variable
					this.page.identifier = 'unique_dynamic_id_12'; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
				};

				(function() { // DON'T EDIT BELOW THIS LINE
					var d = document, s = d.createElement('script');
					s.src = 'https://bipbip1.disqus.com/embed.js';
					s.setAttribute('data-timestamp', +new Date());
					(d.head || d.body).appendChild(s);
				})();
			</script>
			<noscript>
				Please enable JavaScript to view the <a
					href="https://disqus.com/?ref_noscript">comments powered by
					Disqus.</a>
			</noscript>
		</div>
	</div>
</div>