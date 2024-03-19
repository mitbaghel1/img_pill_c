python detect.py --source path/to/your/input/video.mp4 --weights path/to/your/trained/model.pt --save-txt --save-conf --save-crop --project path/to/save/directory --name output_video
python train.py --img 640 --batch 2 --epochs 60 --data data.yaml --cfg models/yolov5s.yaml --name pill_custom_mode
python detect.py --weights runs\train\pill_custom_mode\weights\last.pt --img 640 --conf 0.25 --source ../video.mp4
