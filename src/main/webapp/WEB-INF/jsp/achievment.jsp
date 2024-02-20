<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Achiecment | GAKUSAPO</title>
		<script src="https://cdn.tailwindcss.com"></script>
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	</head>
	<body class="bg-gray-100">
		<jsp:include page="../../header.jsp"/>
		<div class="container w-[1024px] mx-auto mt-5">
			<div class="flex justify-center">
				<div class="w-full max-w-5xl">
					<div class="bg-white p-8 rounded shadow-lg">
						<div id="content" class="m-auto">
							<h1 class="text-xl text-center">○○試験合格！</h1>
							<div class="flex justify-center mt-5">
								<canvas id="achievement_chart" style="max-width: 480px; max-height: 240px;"></canvas>
								<div class="flex flex-col justify-around">
									<div class="text-center bg-blue-100 rounded p-4">
										<p>取り組み期間</p>
										<p>2024/2/24まで</p>
									</div>
									<div class="text-center bg-orange-100 rounded p-4">
										<p>目標の日まで</p>
										<p>あと12日！</p>
									</div>
								</div>
							</div>
							<div class="flex justify-center mt-5">
								<canvas id="material_chart" style="max-width: 480px; max-height: 240px;"></canvas>
							</div>

							<script>
								var ctx = document.getElementById('material_chart');

								var data = {
									labels: ["教材１", "教材２", "教材３"],
									datasets: [{
										label: '達成度',
										data: [25, 68, 46],
										backgroundColor: 'rgba(100, 230, 150, 1)'
									}]
								};

								var options = {
									indexAxis: 'y',
									scales: {
										x:{
											min: 0,
											max: 100
											//beginAtZero: true
										}
									}
								};

								var material_chart = new Chart(ctx, {
									type: 'bar',
									data: data,
									options: options
								});

								var ctx = document.getElementById('achievement_chart');
								var achievement_ratio = (25 + 68 + 46)/3;
								var data = {
									labels: ["達成", "残り"],
									datasets: [{
										data: [achievement_ratio, 100-achievement_ratio],
										backgroundColor: ['rgba(100, 230, 150, 1)', 'rgba(255, 255, 255, 1)'],
										borderColor: ['rgba(75, 215, 125, 1)', 'rgba(230, 230, 230, 1)']
									}]
								};

								var options = {

								};

								var material_chart = new Chart(ctx, {
									type: 'doughnut',
									data: data,
									options: options
								});
							
							</script>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>