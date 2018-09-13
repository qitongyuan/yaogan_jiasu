 #include <iostream>
#include <opencv/highgui.h>
//#include<opencv2\opencv.hpp>
// #include <opencv/highgui.h>
#include <opencv2/highgui/highgui.hpp>
#include <sys/time.h>
#include <time.h>

using namespace std;
using namespace cv;

//计算直方图
void imhist(Mat image, int histogram[]);
//计算CDF
void cumhist(int histogram[], int cumhistogram[]);
//算CDF最小值
void FindCDFMin(int cdfHist[],int *min);
//拉伸图像
void StrerchImage(Mat image,Mat stret_image,int min,int max);
//直方图均衡化
void HistEqualization(Mat cuEq_image,Mat stret_image,int cuStretHist[], int size,int cuMin);
//统计最大最小像素值
void FindMaxAndMin(int info[],int *min,int *max);
//计算NDVI
void NDVI(Mat redImage,Mat nirImage,Mat ndviImage);
//计算阈值
void calculateThreshold(int ndviHis[],int imageSize, int *threshold);
//二值化图像
void OTSU(Mat ndviImage,Mat otsuImage,int threshold);

int main()
{ 	
	// 加载近红外图像
    Mat image = imread("NIR.bmp", CV_LOAD_IMAGE_GRAYSCALE);
	//命名为红外图像
	namedWindow("NIR Image");
	imshow("NIR Image", image);//显示NDVI图像
    int histogram[256];
    imhist(image, histogram);// 生成直方图
	int min;
	int max;
	FindMaxAndMin(histogram,&min,&max);//计算最大最小值
		cout<< min<<max<<endl;
		cout << "最小值："<<min << endl;
		cout << "最大值：" << max << endl;
	//要被拉伸的图片
	Mat stret_image = image.clone();
	StrerchImage(image,stret_image,min,max);//拉伸图像
	//stret_image图像已经被拉伸过

	int stretHistogram[256];
	imhist(stret_image,stretHistogram);//生成直方图
	
	int cuStretHist[256];
	cumhist(stretHistogram,cuStretHist);//计算CDF

	int cuMin;
	FindCDFMin(cuStretHist,&cuMin);//计算CDF最小值

	Mat cuEq_image=image.clone();
	int size=image.rows*image.cols;
	HistEqualization(cuEq_image,stret_image,cuStretHist,size,cuMin);//直方图均衡化


	//加载红外图像
	Mat redImage=imread("Red.bmp",CV_LOAD_IMAGE_GRAYSCALE);
	namedWindow("RED Image");
	imshow("RED Image", redImage);//显示NDVI图像
	int redHistogram[256];
    imhist(redImage, redHistogram);//生成直方图
	int redMin;
	int redMax;
	FindMaxAndMin(redHistogram,&redMin,&redMax);//计算最大最小值
		cout<< redMin<<redMax<<endl;
	Mat redStret_image = redImage.clone();
	StrerchImage(redImage,redStret_image,redMin,redMax);//拉伸图像

	int redStretHistogram[256];
	imhist(redStret_image,redStretHistogram);//生成直方图

	int redcuStretHist[256];
	cumhist(redStretHistogram,redcuStretHist);//计算CDF

	int redcuMin;
	FindCDFMin(redcuStretHist,&redcuMin);//计算CDF最小值

	Mat redcuEq_image=redImage.clone();
	int redSize=redImage.rows*redImage.cols;
	HistEqualization(redcuEq_image,redStret_image,redcuStretHist,redSize,redcuMin);//直方图均衡化



	//加载NDVI图像
	Mat ndviImage=redImage.clone();
	NDVI(redcuEq_image,cuEq_image,ndviImage);//计算NDVI
	namedWindow("NDVI Image");
	imshow("NDVI Image", ndviImage);//显示NDVI图像

	int ndviHis[256];
	imhist(ndviImage,ndviHis);

	int threshold;
	int imageSize=ndviImage.rows*ndviImage.cols;
	calculateThreshold(ndviHis,imageSize, &threshold);//计算阈值
	//threshold=128;
	Mat otsuImage=ndviImage.clone();
	OTSU(ndviImage,otsuImage,threshold);//二值化图像(将整个图像呈现出明显的黑白效果的过程 图像上的像素点的灰度值设置为0或255)
	namedWindow("OTSU Image");
	imshow("OTSU Image", otsuImage);//显示二值化图像
	
    waitKey();
    return 0;

}

/**
*计算直方图
**/
void imhist(Mat image, int histogram[])
{

    // 初始化
    
    for(int i = 0; i < 256; i++)
    {
        histogram[i] = 0;
    }

    // 统计出现的像素值

    for(int y = 0; y < image.rows; y++)

        for(int x = 0; x < image.cols; x++)
			//histogram[(int)image.data[y,x]]++; 
			histogram[ (int)image.at<uchar>(y,x)]++; 
}


/*
*统计最大最小像素值
*/
void FindMaxAndMin(int info[],int *min, int *max)
{
	for(int i=0;i<256;i++)
	{
		if(info[i]!=0)
		{
			*min=i;
			break;
		}
	}
	for(int i=255;i>=0;i--)
	{
		if(info[i]!=0)
		{
			*max=i;
			break;
		}
	}


}


/**
*拉伸图像
**/
void StrerchImage(Mat image,Mat stret_image,int min,int max)
{

	for(int i=0;i<image.rows;i++)
	{
		for(int j=0;j<image.cols;j++)
		{
			//int i =image.data[i,j];
			//stret_image.at<uchar>(i,j)=saturate_cast<uchar>((image.data[i,j]-min)*255/(max-min));
			stret_image.data[i,j]=saturate_cast<uchar>((image.data[i,j]-min)*255/(max-min));
			
		}
	}
}



/**
*计算CDF
**/
void cumhist(int histogram[], int cumhistogram[])
{
    cumhistogram[0] = histogram[0];
    for(int i = 1; i < 256; i++)
    {
        cumhistogram[i] = histogram[i] + cumhistogram[i-1];
    }
}

/**
*计算CDF最小值
**/
void FindCDFMin(int cdfHist[],int *min)
{
	for(int i=0;i<256;i++)
	{
		if(cdfHist[i]!=0)
		{
			*min=cdfHist[i];
			break;
		}
	}
	for(int i=0;i<256;i++)
	{
		if(*min>cdfHist[i])
			*min=cdfHist[i];
	}
}

/**
*直方图均衡化
**/
void HistEqualization(Mat cuEq_image,Mat stret_image,int cuStretHist[], int size,int cuMin)
{
	for(int i=0;i<stret_image.rows;i++)
	{
		for(int j=0;j<stret_image.cols;j++)
		{
			cuEq_image.at<uchar>(i,j)=saturate_cast<uchar>((cuStretHist[stret_image.at<uchar>(i,j)]-cuMin)*255/(size-cuMin));
			//cuEq_image.data[i,j]=saturate_cast<uchar>((cuStretHist[stret_image.at<uchar>(i,j)]-cuMin)*255/(size-cuMin));
		}
	}
}


///**
//*拉伸直方图
//**/
//void HistStretching(Mat image,Mat &stretImage, int max,int min)
//{
//	for(int i=0;i<image.rows;i++)
//	{
//		for(int j=0;j<image.cols;j++)
//		{
//			int i=image.data[i,j];
//			
//			stretImage.at<uchar>(i,j)=saturate_cast<uchar>((image.data[i,j]-min)*255/(float)(max-min));
//			
//		}
//	}
//}
/*计算时间函数*/
double dtime(){
	double tseconds=0.0;
	struct timeval mytime;
	gettimeofday(&mytime,(struct timezone*)0);
	tseconds=(double)(mytime.tv_sec+mytime.tv_usec*1.0e-6);
	return tseconds;
}

/**
*计算NDVI
*968行 1535列
**/
void NDVI(Mat redImage,Mat nirImage,Mat ndviImage)
{
	
	cout << "redImage有多少行数 "<<redImage.rows << endl;
	cout << "redImage有多少列数 " << redImage.cols << endl;
	double tstart,tstop,ttime;
	tstart=dtime();

	// 将mat类型数据拷贝到我的定义的二维数组上进行加速运算
	//rows 968 cols 1535
	int width = nirImage.cols;//1535
	int height = nirImage.rows;//968
	uchar *juzhenR = new uchar[height*width];//定义红外二维矩阵
	uchar *juzhenN = new uchar[height*width];
	uchar *juzhenNDVI = new uchar[height*width];
	//uchar juzhenN[968][1535];//定义近红外二维矩阵
	//uchar juzhenNDVI[968][1535];//提前声明一个待赋值的NDVI矩阵
	unsigned char fRed,  fNIR;
	float ndvi;
    
	for(int i=0;i<height;i++)
	{    
		for(int j=0;j<width;j++)
		{
			juzhenR[i*width+j]=redImage.at<uchar>(i,j);
			juzhenN[i*width +j]=nirImage.at<uchar>(i,j);
			//矩阵付完值之后再从新遍历矩阵进行运算(运算的时候需要用到了并行)
			//ndvi=(fNIR-fRed)/(float)(fNIR+fRed);
			////ndvi=(fNIR-fRed);
			////ndvi=(fNIR)/(float)(fRed);
			////saturate_cast防止数据溢出
			//ndviImage.at<uchar>(i,j)=saturate_cast<uchar>(127.5*(1+ndvi));
		}
	}
	//先遍历行(这里i表示行 j表示列 怀疑因为数据依赖并行不成)height=rows
    //#pragma acc data copyin(juzhenN,juzhenR) copy(juzhenNDVI)
    //#pragma acc loop collapse(2) independent
    //#pragma acc loop gang(32)
    //#pragma acc kernels copyin(juzhenN[0:width*height],juzhenR[0:width*height]),copyout(juzhenNDVI[0:width*height])
    #pragma acc data copyin(juzhenN[0:width*height],juzhenR[0:width*height]),copyout(juzhenNDVI[0:width*height])
	{
        #pragma acc kernels
        //#pragma acc loop independent
		#pragma acc loop seq
		for (int i = 0; i < height; i++)
		{
		//再遍历列width=cols
		#pragma acc loop independent
			//#pragma acc loop seq
			for (int j = 0; j < width; j++)
			{
			//做矩阵运算 
				ndvi=(juzhenN[i * width + j] - juzhenR[i * width + j]) / (float)(juzhenN[i * width + j] + juzhenR[i * width + j]);
				juzhenNDVI[i * width + j] = 127.5*(1 + ndvi);
				if (juzhenNDVI[i * width + j]<0)
				{
					juzhenNDVI[i * width + j] = 0;
				}
				if (juzhenNDVI[i * width + j]>255)
				{
					juzhenNDVI[i * width + j] = 255;
				}
			}
		}
	#pragma acc update host(juzhenNDVI[0:width*height])	
	}


	tstop=dtime();
	ttime=tstop-tstart;
	printf("runningtime %f(s)\n",ttime);
	//将juzhenNDVI赋值给Mat ndviImage
	Mat ndvijuzhen(height, width, CV_8UC1, (uchar *)juzhenNDVI);
	ndvijuzhen.copyTo(ndviImage);
	//ndviImage = ndvijuzhen.clone();
}

/**
*计算阈值
**/
void calculateThreshold(int ndviHis[],int imageSize, int *threshold)
{
	float w0=0;
	float u0=0;
	float w1=0;
	float u1=0;
	float u=0;
	float g=0;
	//int t=0;
	float gArr[256];
	for(int i=0;i<256;i++)
	{
		if(ndviHis[i]!=0)
		{
			//t=ndviHis[i];
			double whiteColor=0;
			double whiteCount=0;
			double blackColor=0;
			double blackCount=0;
			for(int j=0;j<i;j++)//前景
			{
				whiteColor+=j*ndviHis[i];
				whiteCount+=ndviHis[i];
				u0=whiteColor/whiteCount;
				w0=whiteCount/imageSize;
			}
			for(int k=i;k<256;k++)//背景
			{
				blackColor+=k*ndviHis[k];
				blackCount+=ndviHis[k];
				u1=blackColor/blackCount;
				w1=blackCount/imageSize;
			}
			g=w0*w1*(u0-u1)*(u0-u1);
			gArr[i]=g;
		}
		//cout<<"阈值："<<g<<endl;
	}

	int gMax;
	gMax=gArr[0];
	for(int i=0;i<256;i++)//选取最大阈值
	{
		if(gArr[i]>gMax)
		{
			gMax=gArr[i];
			*threshold=i;
		}
	}
	cout<<"阈值："<<*threshold<<endl;
}

/**
*二值化图像
**/
void OTSU(Mat ndviImage,Mat otsuImage,int threshold)
{
	for(int i=0;i<ndviImage.rows;i++)
	{
		for(int j=0;j<ndviImage.cols;j++)
		{
			if(ndviImage.at<uchar>(i,j)>=threshold)
			{
				otsuImage.at<uchar>(i,j)=saturate_cast<uchar>(255);
			}
			else
			{
				otsuImage.at<uchar>(i,j)=saturate_cast<uchar>(0);
			}
		}
	}
}
