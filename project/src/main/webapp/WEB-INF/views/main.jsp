<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<meta name="description"
	content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>
<link href="css/style.css" rel="stylesheet">
</head>

<body>
	<div id="container">
		<div class="header">
			<header class="header_tit">
				<h1 class="logo">
					<a href="https://m.naver.com/" class="lnk_logo" title="네이버"> <span
						class="spr_bi ico_n_logo">네이버</span>
					</a> <a href="myreservation" class="lnk_logo" title="예약"> <span
						class="spr_bi ico_bk_logo">예약</span>
					</a>
				</h1>
				<a href="bookinglogin" class="btn_my"> <span
					class="viewReservation" title="예약확인">예약확인</span>
				</a>
			</header>
		</div>
		<hr>
		<div class="event">
			<div class="section_visual">
				<div class="group_visual">
					<div class="container_visual">
						<div class="prev_e" style="display: none;">
							<div class="prev_inn">
								<a href="#" class="btn_pre_e" title="이전"> <i
									class="spr_book_event spr_event_pre">이전</i>
								</a>
							</div>
						</div>
						<div class="nxt_e" style="display: none;">
							<div class="nxt_inn">
								<a href="#" class="btn_nxt_e" title="다음"> <i
									class="spr_book_event spr_event_nxt">다음</i>
								</a>
							</div>
						</div>
						<div>
							<div class="container_visual">
								<!-- 슬라이딩기능: 이미지 (type = 'th')를 순차적으로 노출 -->
								<ul class="visual_img">
									<img alt="${items[0].productDescription }" style="width: 100%; height: 170px;"
										src="${items[0].productImageUrl }">								
								</ul>
							</div>
							<span class="nxt_fix" style="display: none;"></span>
						</div>
					</div>
				</div>
			</div>
			<div class="section_event_tab">
				<ul class="event_tab_lst tab_lst_min">
					<li class="item" data-category="0"><a class="anchor active">
							<span>전체리스트</span>
					</a></li>
				</ul>
			</div>
			<div class="section_event_lst">
				<p class="event_lst_txt">
					바로 예매 가능한 행사가 <span class="pink">${totalCount }개</span> 있습니다
				</p>
				<div class="wrap_event_box" data-cnt=${items.size() }>
					<!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->

					<ul class="lst_event_box">
						<li class="item" data-id="${items[0].displayInfoId }"><a
							href="detail?displayInfoId=${items[0].displayInfoId }" class="item_book">
								<div class="item_preview">
									<img alt="${items[0].productDescription }" class="img_thumb"
										src="${items[0].productImageUrl}"> <span
										class="img_border"></span>
								</div>
								<div class="event_txt">
									<h4 class="event_txt_tit">
										<span>${items[0].productDescription }</span> <small class="sm">${items[0].placeName }</small>
									</h4>
									<p class="event_txt_dsc">${items[0].productContent }</p>
								</div>
						</a></li>
						<li class="item" data-id="${items[1].displayInfoId }"><a
							href="detail?displayInfoId=${items[1].displayInfoId}" class="item_book">
								<div class="item_preview">
									<img alt="${items[1].productDescription }" class="img_thumb"
										src="${items[1].productImageUrl }" /> <span
										class="img_border"></span>
								</div>
								<div class="event_txt">
									<h4 class="event_txt_tit">
										<span>${items[1].productDescription }</span> <small class="sm">${items[1].placeName }
										</small>
									</h4>
									<p class="event_txt_dsc">${items[1].productContent}</p>
								</div>
						</a></li>
					</ul>
					<ul class="lst_event_box">
						<li class="item" data-id="${items[2].displayInfoId }"><a
							href="detail?displayInfoId=${items[2].displayInfoId}" class="item_book">
								<div class="item_preview">
									<img alt="${items[2].productDescription }" class="img_thumb"
										src="${items[2].productImageUrl }"> <span
										class="img_border"></span>
								</div>
								<div class="event_txt">
									<h4 class="event_txt_tit">
										<span>${items[2].productDescription }</span> <small class="sm">${items[2].placeName }
										</small>
									</h4>
									<p class="event_txt_dsc">${items[2].productContent }</p>
								</div>
						</a></li>
						<li class="item" data-id="${items[3].displayInfoId }"><a
							href="detail?displayInfoId=${items[3].displayInfoId }" class="item_book">
								<div class="item_preview">
									<img alt="${items[3].productDescription }" class="img_thumb"
										src="${items[3].productImageUrl }"> <span
										class="img_border"></span>
								</div>
								<div class="event_txt">
									<h4 class="event_txt_tit">
										<span>${items[3].productDescription }</span> <small class="sm">${items[3].placeName }
										</small>
									</h4>
									<p class="event_txt_dsc">${items[3].productContent }</p>
								</div>
						</a></li>
					</ul>
					<!-- 더보기 -->
					<div class="more">
						<button class="btn" id="moreBtn">
							<span>더보기</span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<div class="gototop">
			<a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span>
			</a>
		</div>
		<div class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및
				환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.2/handlebars.min.js"
		integrity="sha256-ngJY93C4H39YbmrWhnLzSyiepRuQDVKDNCWO2iyMzFw="
		crossorigin="anonymous"></script>

	<script type="rv-template" id="promotionItem">
    <li class="item" style="background-image: url(http://211.249.62.123/productImages/${productId}/${productImageId});">
        <a href="#"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
            <div class="event_txt">
                <h4 class="event_txt_tit"></h4>
                <p class="event_txt_adr"></p>
                <p class="event_txt_dsc"></p>
            </div>
        </a>
    </li>
    </script>

	<script type="rv-template" id="itemList">
        <li class="item">
            <a href="detail?id=${id}" class="item_book">
                <div class="item_preview">
                    <img alt="${description}" class="img_thumb" src="http://211.249.62.123/productImages/${id}?type=th">
                    <span class="img_border"></span>
                </div>
                <div class="event_txt">
                    <h4 class="event_txt_tit"> <span>${description}</span> <small class="sm">${placeName}</small> </h4>
                    <p class="event_txt_dsc">${content}</p>
                </div>
            </a>
        </li>
    </script>

	<script type="text/template" id="categoryList">
		<li class="item" data-category={{id}}><a class="anchor"> <span>{{name}}</span>
		</a></li>
    </script>
    
    <script type="text/template" id="productsList">
		<li class="item" data-id="{{displayInfoId}}">
			<a href="detail?displayInfoId={{displayInfoId}}" class="item_book">
				<div class="item_preview">
					<img alt="{{productDescription}}" class="img_thumb"
							src="{{productImageUrl}}"> <span class="img_border"></span>
				</div>
				<div class="event_txt">
					<h4 class="event_txt_tit">
						<span>{{productDescription}}</span> <small class="sm">{{placeName}}</small>
					</h4> <p class="event_txt_dsc">{{productContent}}</p>
				</div>
		</a></li>			
    </script>
	<script type="text/template" id="imageSlide">
		<img alt="{{ productId}}" style="width: 100%; height: 170px;"
		src="{{productImageUrl}}">
	</script>
	<script>
	const reservationEmail = "${sessionScope.email}";
	
	
	window.addEventListener('load', function(){
		console.log(reservationEmail);
		if(reservationEmail!=="") document.querySelector(".btn_my").innerText = reservationEmail;
		else document.querySelector(".btn_my").innerText = "예약확인";
	});
	
	document.querySelector(".btn_my").addEventListener("click", function(){
		if(reservationEmail!=="") this.href="myreservation?email="+reservationEmail;
	});
		const xhr = new XMLHttpRequest();
		function getPromotion(){
			window.addEventListener('load', function(){ 
				xhr.open('GET', '/project/api/promotions');
				xhr.send();
				xhr.onload = function(e) {
					if (xhr.status === 200) {
						let result = JSON.parse(xhr.responseText).items;
						slide(result);
					} else { 
						console.log('Error!');
					}
				};	 
			});
		}
		getPromotion();	
		fetchCategories();
/* 		window.addEventListener('load', function(){
			getPromotion();
		});
 */		
	function fetchCategories(){
		xhr.open('GET', '/project/api/categories');
		// Request를 전송한다
		xhr.send();
		xhr.onload = function(e) {
			// status는 response 상태 코드를 반환 : 200 => 정상 응답
			if (xhr.status === 200) {
				let result = JSON.parse(xhr.responseText).categories;
				xhr.open('GET', '/project/api/products');
				xhr.send();
				xhr.onload = function(e) {
					if (xhr.status === 200) {
						let result = JSON.parse(xhr.responseText);
						changeTemplate(result);
						
					} else {
						console.log('Error!');
					}
				};	
				getCategories(result);
			} else {
				console.log('Error!');
			}
		};
 }
		function getCategories(data) {
			var template = document.querySelector("#categoryList").innerText;
			var bindTemplate = Handlebars.compile(template);
			var resultHtml = data.reduce(function(prev, next) {
				return prev + bindTemplate(next);
			}, "");
			var node = document.querySelector(".section_event_tab").firstElementChild;
			node.innerHTML = node.innerHTML + resultHtml;

			var menuList = node.querySelectorAll(".item");
			var menuLen = menuList.length;

		
			for (var i = 0; i < menuLen; i++) {
				var search = {
					categoryId : 0,
					cnt : 0
				}
				menuList[i].addEventListener("click", function() {
					document.querySelector(".wrap_event_box").setAttribute("data-cnt", 0);
					document.getElementById("moreBtn").style.display="";
					
					var current = document.getElementsByClassName("active");
					if (current.length > 0) { 
					    current[0].className = current[0].className.replace(" active", "");
					  }
					this.firstElementChild.className += " active";
					search.categoryId = this.getAttribute("data-category");
					search.type="";
					search.cnt = 0;
					console.log(search);
					fetchProducts(search);
				});
			}
			
			var more = document.getElementById("moreBtn").addEventListener("click", function(){
				
				var parent = document.querySelector(".wrap_event_box").querySelectorAll(".lst_event_box");
// 				var last = parent[parent.length - 1].lastElementChild.getAttribute("data-id");
 				var last = document.querySelector(".wrap_event_box").getAttribute("data-cnt");
				search.cnt = last;
				search.type="more";
				console.log("더보기 !!!! ",last);
				
				fetchProducts(search);
			});
		}
		
		function fetchProducts(data) {
			//console.log('fetchProduct==조건 ', data);
			if (data.categoryId == 0) {
				xhr.open('GET',`/project/api/products?start=\${data.cnt}`);
				
			} else {
				xhr.open('GET',`/project/api/products?categoryId=\${data.categoryId}&start=\${data.cnt}`);
			}
			// Request를 전송한다
			xhr.send();
			xhr.onload = function(e) {
				// status는 response 상태 코드를 반환 : 200 => 정상 응답
				if (xhr.status === 200) {
					let result = JSON.parse(xhr.responseText);
					document.querySelector(".wrap_event_box").setAttribute("data-cnt", parseInt(data.cnt)+result.items.length);

					if(data.type==="more") {
						result.type="more";
					}
					//템플릿 변경 
					changeTemplate(result);
				} else {
					console.log('Error!');
				}
			};
		}
		
		function changeTemplate(data){ // 카테고리 변경 
			console.log(data);
			console.log(data.items[0].productImageUrl);
			fetchImageUrl(data.items[0].productImageUrl);
			
			let count= document.querySelector(".section_event_lst").firstElementChild.childNodes[1].innerText = data.totalCount+"개";
			let listBox = document.querySelector(".wrap_event_box").querySelectorAll(".lst_event_box");
			let template_p = document.querySelector("#productsList").innerText;
			let bindTemplate = Handlebars.compile(template_p);

			var resultHtml1="", resultHtml2=""; 
			
			data.items.forEach(function (item, index) {
				if(index<2)resultHtml1 += bindTemplate(item);
				else resultHtml2 +=bindTemplate(item);
			});
			if(data.type === "more"){
				listBox[0].innerHTML += resultHtml1;
				listBox[1].innerHTML += resultHtml2;
				
				let moreAction = document.getElementById("moreBtn");
				let total = document.getElementsByClassName("pink")[0].textContent.replace("개","");
				let last = document.querySelector(".wrap_event_box").getAttribute("data-cnt");
				console.log(" 총 갯수 ",total,"로드한 갯수 ", last);
 				if(total == last){
 					moreAction.style.display = "none";
 				}else moreAction.style.display="";
				return;
			}	
			listBox[0].innerHTML = resultHtml1;
			listBox[1].innerHTML = resultHtml2;
		}
		
		function slide(data){
			var template = document.querySelector("#imageSlide").innerText;
			var bindTemplate = Handlebars.compile(template);
			var resultHtml = data.reduce(function(prev, next) {
				return prev + bindTemplate(next);
			}, "");
	        const ul = document.querySelector('.visual_img');
			ul.innerHTML = resultHtml;

	        var curIndex = 0;
	        setInterval(function(){
                ul.style.transition = '0.2s';
                ul.style.transform = "translate3d(-"+414*(curIndex+1)+"px, 0px, 0px)";
 
                curIndex++;
 
                if(curIndex === data.length){
                    setTimeout(function(){
                        ul.style.transition = '0s';
                        ul.style.transform = "translate3d(0px, 0px, 0px)";
                    },201)
                    curIndex = 0;
                }
 
            },1000);
		}
		function fetchImageUrl(saveFileName){
			xhr.open('GET',"/image?fileName="+saveFileName);
			xhr.send();
			xhr.onload=function(e){
				if(xhr.status === 200){
					console.log(xhr.responseText);
					return xhr.responseText;
				}else console.log(xhr);
			}
		}
	</script>
</body>

</html>
