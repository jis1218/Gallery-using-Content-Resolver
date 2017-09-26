여기서 사용한 클래스
RecyclerView, ContentResolver, 권한 설정(READ_EXTERNAL_STORAGE)

사진파일 가져오는 코드
```java
private List<String> load(){
        List<String> list = new ArrayList<>();
        ContentResolver resolver = getContentResolver();
        Uri uri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;
        String proj[] = {
                MediaStore.Images.Thumbnails.DATA
        };
        Cursor cursor = resolver.query(uri, proj, null, null, null);
        if(cursor != null){
            while(cursor.moveToNext()){
                int index = cursor.getColumnIndex(proj[0]);
                String path = cursor.getString(index);
                list.add(path);
            }
        }

        return list;
    }
```

강의 시간에는 intent.putExtra()에 Uri를 담을 때 그냥 담지 않고 String으로 반환하여 넘겨줬는데 그럴 필요 없이
putExtra는 두번째 파라미터로 Parcelable를 담을 수 있고 Uri 클래스는 Parcelable interface를 implementing 하므로
그냥 Uri를 넣어주면 된다.

```java
itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    //intent.putExtra("imagePath", uri.getPath());
                    intent.putExtra("imagePath", uri);
                    ((Activity) context).setResult(Activity.RESULT_OK, intent);
                    ((Activity) context).finish();
                }
            });

protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
          case REQ_GALLERY:
                if (resultCode == RESULT_OK) {
                    //String datadata = data.getStringExtra("imagePath");
                    Uri imageUri = data.getParcelableExtra("imagePath");
                    imageView.setImageURI(imageUri);
                }
              }
            }
```
