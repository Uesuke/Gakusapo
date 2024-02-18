//教材詳細入力欄に関するボタンに関するメソッド

var num_materials = 1;

function addForm() {
	//テンプレートからリストアイテムを複製
	const template = document.getElementById("formTemplate");
	const newItem = template.content.cloneNode(true);
		
	// 子要素を指定しname属性の値を変更
    newItem.querySelector('#materialId_').name = 'materialId_' + num_materials;
    newItem.querySelector('#startFrom_').name = 'startFrom_' + num_materials;
    newItem.querySelector('#endTo_').name = 'endTo_' + num_materials;
    
    // 子要素を指定しid属性の値を変更
    newItem.querySelector('#materialId_').id = 'materialId_' + num_materials;
    newItem.querySelector('#startFrom_').id = 'startFrom_' + num_materials;
    newItem.querySelector('#endTo_').id = 'endTo_' + num_materials;
    newItem.querySelector('#formItem_').id = 'formItem_' + num_materials;

    // 新しいリストアイテムを追加
    document.getElementById('formList').appendChild(newItem);
    
    num_materials++;
    
    // hidden属性をもつinputタグのvalueにitem数を設定
    const inputNum = document.getElementById('num_materials');
    inputNum.value = num_materials;
    
	//削除ボタンを表示状態に設定
	document.getElementById('deleteBtn').style.visibility = 'visible';

    console.log('item added!')
    console.log('num:' + num_materials);
    
};

function deleteForm() {
	//numが2以上でなければ削除は実行せずに早期リターン(不具合の防止)
	if(num_materials <= 1){
		return true;
	}
	//idxに最後尾のインデックスを設定し、
	const idx = num_materials - 1;
	
	//親要素のulから該当するアイテムを削除
	const parent = document.getElementById('formList');
	const target = document.getElementById('formItem_' + idx);
	parent.removeChild(target);
	
	//numをデクリメント
	num_materials--;
	
	//numが1以下になったら削除ボタンを非表示に設定
	if(num_materials <= 1){
		document.getElementById('deleteBtn').style.visibility = 'hidden';
	}
}

