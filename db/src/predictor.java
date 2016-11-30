import java.util.ArrayList;


public class predictor {
		public ArrayList<Float> values;
			
			private ArrayList<Float> trainingSet;
			
			public float calculation(ArrayList<Float> myvals){		//!< returns average
				float sum = 0.00f;
				for(Float currValue: myvals){
					sum+=currValue;
				}
				return (sum/myvals.size());
			}	

			private void genTrainingSet(int tillIndex){				//!< Makes the training set
				trainingSet = new ArrayList<Float>();
				trainingSet.addAll(values.subList(0, tillIndex));
			}
			
			private float getSum(ArrayList<Float> myvals){			//!< Get sum of values
				float sum = 0;
				for(Float currVal: myvals){
					sum += currVal;
				}
				return sum;
			}
			private float getSqSum(ArrayList<Float> myvals){		//!< Get sum of squares of values
				float sum = 0;
				for(Float currVal: myvals){
					sum += (Math.pow(currVal, 2.00));
				}
				return sum;
			}
			private float standardDeviation(ArrayList<Float> myvals){		//!< Get std. Deviation of set
				float stdDev;
				float sqSum = getSqSum(myvals);
				float sum = getSum(myvals);
				float N = myvals.size();
				stdDev = (sqSum - ((float)Math.pow(sum,2.00f)/N))/N;
				stdDev = (float) Math.pow(stdDev,0.50f);
				return stdDev;
			}
			private float correlation(ArrayList<Float> myvals){				//!< Find correlation values
				float r;
				float sumxy = 0;
				float sumSqY = 0;
				float sumSqX = 0;
				float sumX = 0;
				float sumY = 0;
				float N = myvals.size();
				for(int index = 0; index <myvals.size(); index++ ){
					sumxy += ((index+1.00f)*myvals.get(index));
					sumSqY += Math.pow(myvals.get(index),2.00f);
					sumSqX += Math.pow((index+1.00f),2.00f);
					sumX += (index+1);
					sumY += myvals.get(index);
				}
				float numerator = (float) sumxy - ((sumX*sumY)/N);
				float denominator01 = (float) ((float) sumSqX - ((Math.pow(sumX, 2.0f))/N));
				float denominator02 = (float) ((float) sumSqY - ((Math.pow(sumY, 2.0f))/N));
				float denominator = (float) Math.pow((denominator01*denominator02), 0.50f);
				r = (float) numerator/denominator;
				return r;
			}
			
			private float yintercept,slope;
			
			private void equationFinder(ArrayList<Float> myvals){	//!< Generate equation for linear regression
				//
				ArrayList<Float> indices = new ArrayList<Float>();
				for(int index = 0; index<myvals.size();index++){
					indices.add((float)index+1);
				}
				//
				float r = correlation(myvals);
				float MY = calculation(myvals);
				float MX = calculation(indices);
				float SY = standardDeviation(myvals);
				float SX = standardDeviation(indices);
				
				slope = (float) (r*SY)/SX;
				yintercept = (float) MY - (slope*MX);
			}
			
			public float predict(ArrayList<Float> myvals, float xValue){		//!< Return final predicted value
				equationFinder(myvals);
				float prediction;
				prediction = slope*(xValue+1) + yintercept;
				return prediction;
			}
		
}
