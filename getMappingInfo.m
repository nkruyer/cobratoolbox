function[reac,MicRea,BinOrg,patOrg,ReacPat,reacNumb,ReacSet,ReacTab,ReacAbun,reacnumber]=getMappingInfo(models,infoPath,filename,patnumb)
% This function automatically Extracts information from strain abundances in 
% different individuals and combines this information into different tables. 
%
% INPUT 
%   models             nx1 cell array that contains n microbe models in
%                      COBRA model structure format
%   infoPath           char with path of directory from where to retreive information
%   filename           char with name of file from which to to retreive information
%   patnumb            number of individuals in the study
%                     
% OUTPUT               
%   reac               cell array with all the unique set of reactions 
%                      contained in the models
%   MicRea             binary matrix assessing presence of set of unique 
%                      reactions for each of the microbes 
%   BinOrg             binary matrix asessing presence of specific strains in 
%                      different individuals
%   ReacPat            matrix with number of reactions per individual 
%                     (organism resolved) 
%   ReacSet            matrix with names of reactions of each individual
%   ReacTab            char with names of individuals in the study 
%   ReacAbun           binary matrix with presence/absence of reaction per 
%                      individual: to compare different individuals
%   reacnumber         number of unique reactions of each individual 
%
% Author: Federico Baldini 2017-2018

 reac={}; %array with unique set of all the reactions present in the models
for i = 1:(length(models)-1)%find the unique set of all the reactions contained in the models
    smd=models{i,1};
    allreac=smd.rxns;
    i=i+1;
    smd=models{i,1};
    allreac1=smd.rxns;
    reaclist=unique(union(allreac,allreac1));
    reac=union(reac,reaclist);
end

%Code to detect reaction presence in each model and create inary matrix 
%assessing presence of set of unique reactions for each of the microbes

MicRea = zeros(length(models),length(reac));

mdlt=length(models);
rclt=length(reac);
parfor i = 1:mdlt 
    model=models{i,1};
    for j = 1:rclt
        if ismember(reac(j),model.rxns)
        MicRea(i,j)= 1;
        end
    end
end

%creating binary table for abundances

filename=strcat(infoPath,{filename});
filename=cell2mat(filename);
[binary]=readtable(filename);
s=size(binary);
s=s(1,2);
binary=binary(:,3:s); %removing model info and others 
binar=table2cell(binary);

lgi=length(binar(:,1));
lgj=length(binar(1,:));
parfor i=1:lgi
    for j=1:lgj
        if table2array(binary(i,j))~=0
           binary{i,j}=1;
        end
    end
end

BinOrg=binary;

%Compute number of reactions per individual (species resolved)

ReacPat=zeros(length(table2cell(BinOrg(:,1))),length(table2cell(BinOrg(1,:))));
cleantabc=table2cell(BinOrg);
for j = 1:length(table2cell(BinOrg(1,:)))
    for i = 1:length(table2cell(BinOrg(:,1)))
        temp=cell2mat(cleantabc(i,j));
        if temp == 1 
            ReacPat(i,j)=sum(MicRea(i,:));
        end
    end
end

%Computing overall (non unique) number of reactions per individual

totReac=[];
for i = 1:length(ReacPat(1,:))
    totReac(i,1)= sum(ReacPat(:,i));
end

%Computing number of reactions per organism

reacNumb=[];
for i = 1:length(MicRea(:,1))
    reacNumb(i,1)=sum(MicRea(i,:));
end

%Computing number of organism per individual

patOrg=[];
for i = 1:length(cleantabc(1,:))
    patOrg(i,1) = sum(table2array(BinOrg(:,i)));
end
patOrg=patOrg';

%number and names of UNIQUE reactions per patient

[abundance]=readtable(filename);
ReacSet={};
reacnumber=[];

for j = 1 : length(table2cell(BinOrg(1,:)))
    abunvec=[];
    reacvec=[];
    for i = 1 : length(table2cell(BinOrg(:,1)))
        if (cell2mat(table2cell(BinOrg(i,j)))) == 1
            model=models{i,1};
            reacvec= vertcat(reacvec,model.rxns);
            abunvec((length(abunvec)+1) : ((length(abunvec))+ length(model.rxns)))=  table2array(abundance(i,j+2));
        end
    end
    
    completeset(1:length(reacvec),j)=  reacvec; %to get lists of reactions per each individual
    completeabunorm(1:length(reacvec),j) = abunvec';%matrix with abundance coefficients for normalization 
    ReacSet(1:length(unique(reacvec)),j)= unique(reacvec); %to get lists of reactions per each individual
    reacnumber(j)= length(unique(reacvec));
end

reacLng=length(reac);

parfor j=1:patnumb
    for i=1:reacLng
        indrxn = find(strncmp(reac(i,1), completeset(:,j), length(char(reac(i,1)))));
        numbtab(i,j)=sum(completeabunorm(indrxn));
    end
end

ReacAbun = [reac,num2cell(numbtab)];


%presence/absence of reaction per patient: to compare different patients
%with pCoA
ReacTab = zeros(length(reac),length(ReacPat(1,:)));


parfor k = 1 : length(ReacPat(1,:))
 match= []
    for i = 1 : length(reac)
        for j = 1 : length(ReacSet(:,1))
            if strcmp(reac(i),ReacSet(j,k)) == 1 %the 2 reactions are equal
               match(i) = 1;
            end
        end
    end
 ReacTab(:,k)= match
end
end

