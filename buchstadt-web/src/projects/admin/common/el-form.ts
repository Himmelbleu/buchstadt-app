import type { FormRules } from "element-plus";

/**
 * 书籍表单规则
 */
export const buchFormRules = reactive<FormRules>({
  name: [
    { required: true, message: "请输入书籍名称", trigger: "change" },
    { min: 2, max: 50, message: "长度在 2 ~ 50", trigger: "change" }
  ],
  postDate: [
    {
      type: "date",
      required: true,
      message: "请选择一个日期",
      trigger: "blur"
    }
  ],
  profile: [
    { required: true, message: "请输入书籍的简介", trigger: "blur" },
    { min: 10, max: 1500, message: "长度在 10 ~ 1000", trigger: "change" }
  ],
  cover: [{ required: true, message: "请输入书籍封面的 URL", trigger: "blur" }]
});

/**
 * 书籍表单数据
 */
export const buchFormData = reactive({
  name: "",
  postDate: "",
  profile: "",
  cover: "",
  price: 10,
  discount: 0.5,
  type: "literature",
  isPrime: 0,
  publisherId: "",
  tags: [{ tag: "" }],
  previews: [{ url: "" }],
  authors: [{ author: "" }]
});

export const buchPrimeOps = ref([
  {
    value: 1,
    label: "是"
  },
  {
    value: 0,
    label: "否"
  }
]);

export const buchTypeOps = ref([
  {
    value: "literature",
    label: "文学"
  },
  {
    value: "living",
    label: "生活"
  },
  {
    value: "computer",
    label: "计算机"
  },
  {
    value: "language",
    label: "外语"
  },
  {
    value: "business",
    label: "经营"
  },
  {
    value: "motivation",
    label: "励志"
  },
  {
    value: "social",
    label: "社科"
  },
  {
    value: "academic",
    label: "学术"
  },
  {
    value: "children",
    label: "少儿"
  },
  {
    value: "origin",
    label: "原版"
  },
  {
    value: "technology",
    label: "科技"
  },
  {
    value: "examine",
    label: "考试"
  },
  {
    value: "art",
    label: "艺术"
  },
  {
    value: "encyclopedia",
    label: "生活百科"
  }
]);

export const dateShortcuts = [
  {
    text: "今天",
    value: new Date()
  },
  {
    text: "昨天",
    value: () => {
      const date = new Date();
      date.setTime(date.getTime() - 3600 * 1000 * 24);
      return date;
    }
  },
  {
    text: "一周前",
    value: () => {
      const date = new Date();
      date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
      return date;
    }
  },
  {
    text: "一月前",
    value: () => {
      const date = new Date();
      date.setTime(date.getTime() - 3600 * 1000 * 24 * 30);
      return date;
    }
  },
  {
    text: "一年前",
    value: () => {
      const date = new Date();
      date.setTime(date.getTime() - 3600 * 1000 * 24 * 365);
      return date;
    }
  }
];

export const disabledDate = (time: Date) => {
  return time.getTime() > Date.now();
};
