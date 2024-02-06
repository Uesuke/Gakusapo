function extractDate(inputElement) {
	var dateInput = inputElement.value;
	var selectedDate = new Date(dateInput);
	
	// Extract year, month, and day
	var year = selectedDate.getFullYear();
	var month = (selectedDate.getMonth() + 1).toString().padStart(2, '0'); // Month is zero-based
	var day = selectedDate.getDate().toString().padStart(2, '0');
	var dateOutput = year + "-" + month + "-" + day;
	
	//入力欄の次のinput(hiddenタイプ推奨)に日付文字列を値として追加
	inputElement.nextElementSibling.value = dateOutput;
}