USE [master]
GO
/****** Object:  Database [lab02]    Script Date: 8/30/2020 11:06:41 PM ******/
CREATE DATABASE [lab02]
GO
ALTER DATABASE [lab02] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [lab02].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [lab02] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [lab02] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [lab02] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [lab02] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [lab02] SET ARITHABORT OFF 
GO
ALTER DATABASE [lab02] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [lab02] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [lab02] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [lab02] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [lab02] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [lab02] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [lab02] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [lab02] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [lab02] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [lab02] SET  DISABLE_BROKER 
GO
ALTER DATABASE [lab02] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [lab02] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [lab02] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [lab02] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [lab02] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [lab02] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [lab02] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [lab02] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [lab02] SET  MULTI_USER 
GO
ALTER DATABASE [lab02] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [lab02] SET DB_CHAINING OFF 
GO
ALTER DATABASE [lab02] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [lab02] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [lab02] SET DELAYED_DURABILITY = DISABLED 
GO
USE [lab02]
GO
/****** Object:  Table [dbo].[book]    Script Date: 8/30/2020 11:06:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[book](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](55) NOT NULL,
	[description] [text] NOT NULL,
	[img] [varchar](255) NULL,
	[quantity] [int] NOT NULL,
	[price] [decimal](18, 2) NOT NULL,
	[author] [varchar](55) NOT NULL,
	[category_id] [int] NOT NULL,
	[status] [bit] NOT NULL CONSTRAINT [DF_book_status]  DEFAULT ((1)),
	[create_at] [datetime] NULL CONSTRAINT [DF_book_create_at]  DEFAULT (sysutcdatetime()),
	[update_at] [datetime] NULL CONSTRAINT [DF_book_update_at]  DEFAULT (sysutcdatetime()),
 CONSTRAINT [PK_book] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[category]    Script Date: 8/30/2020 11:06:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[create_at] [datetime] NULL CONSTRAINT [DF_category_create_at]  DEFAULT (sysutcdatetime()),
	[update_at] [datetime] NULL CONSTRAINT [DF_category_update_at]  DEFAULT (sysutcdatetime()),
 CONSTRAINT [PK_category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[checkout]    Script Date: 8/30/2020 11:06:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[checkout](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[discount_id] [int] NULL,
	[user_id] [int] NOT NULL,
	[create_at] [datetime] NULL,
	[update_at] [datetime] NULL,
 CONSTRAINT [PK_checkout] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[checkout_book]    Script Date: 8/30/2020 11:06:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[checkout_book](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[checkout_id] [int] NOT NULL,
	[book_id] [int] NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [decimal](18, 2) NOT NULL,
 CONSTRAINT [PK_checkout_book] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[discount]    Script Date: 8/30/2020 11:06:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[discount](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[offer] [int] NOT NULL,
	[isUsed] [bit] NOT NULL CONSTRAINT [DF_discount_isUsed]  DEFAULT ((0)),
	[status] [bit] NOT NULL CONSTRAINT [DF_discount_status]  DEFAULT ((1)),
	[create_at] [datetime] NULL CONSTRAINT [DF_discount_create_at]  DEFAULT (sysutcdatetime()),
	[update_at] [datetime] NULL CONSTRAINT [DF_discount_update_at]  DEFAULT (sysutcdatetime()),
	[code] [varchar](255) NOT NULL,
 CONSTRAINT [PK_discount] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[role]    Script Date: 8/30/2020 11:06:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[role](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](15) NOT NULL,
	[create_at] [datetime] NULL CONSTRAINT [DF_role_create_at]  DEFAULT (sysutcdatetime()),
	[update_at] [datetime] NULL CONSTRAINT [DF_role_update_at]  DEFAULT (sysutcdatetime()),
 CONSTRAINT [PK_role] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[shopping]    Script Date: 8/30/2020 11:06:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[shopping](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[create_at] [datetime] NULL,
	[update_at] [datetime] NULL,
	[user_id] [int] NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_shopping] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[shopping_book]    Script Date: 8/30/2020 11:06:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[shopping_book](
	[shopping_id] [int] NOT NULL,
	[book_id] [int] NOT NULL,
	[quantity] [int] NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_shopping_book] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[user]    Script Date: 8/30/2020 11:06:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[user](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](55) NOT NULL,
	[encrypted_password] [varchar](255) NOT NULL,
	[name] [varchar](55) NOT NULL,
	[phone] [varchar](12) NULL,
	[role_id] [int] NOT NULL,
	[img] [varchar](255) NULL,
	[status] [bit] NOT NULL CONSTRAINT [DF_user_status]  DEFAULT ((1)),
	[create_at] [datetime] NULL CONSTRAINT [DF_user_create_at]  DEFAULT (sysutcdatetime()),
	[update_at] [datetime] NULL CONSTRAINT [DF_user_update_at]  DEFAULT (sysutcdatetime()),
 CONSTRAINT [PK_user] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[book] ON 

INSERT [dbo].[book] ([id], [title], [description], [img], [quantity], [price], [author], [category_id], [status], [create_at], [update_at]) VALUES (12, N'The Alchemist', N'The story of the treasures Santiago finds along the way teaches us, as only a few stories can, about the essential wisdom of listening to our hearts, learning to read the omens strewn along life''s path, and, above all, following our dreams. Acclaimed illustrator Daniel Sampere brings Paulo Coelho''s classic to a new life in this gorgeously illustrated graphic novel adaptation.', N'1.jpg', 18, CAST(21.33 AS Decimal(18, 2)), N'Paulo Coelho ', 8, 1, CAST(N'2020-08-26 23:44:28.217' AS DateTime), CAST(N'2020-08-30 22:15:20.817' AS DateTime))
INSERT [dbo].[book] ([id], [title], [description], [img], [quantity], [price], [author], [category_id], [status], [create_at], [update_at]) VALUES (1003, N'The Hidden Messages in Water', N'The Hidden Messages in Water is a 2005 New York Times Bestseller book, written by Masaru Emoto advancing the pseudoscientific idea that the molecular structure of water is changed by the presence of human consciousness nearby, backed by "exhaustive and wildly unscientific research" claiming to back this conjecture.', N'2.jpg', 16, CAST(24.00 AS Decimal(18, 2)), N'Masaru Emoto', 10, 1, CAST(N'2020-08-27 18:59:36.907' AS DateTime), CAST(N'2020-08-30 22:18:54.200' AS DateTime))
INSERT [dbo].[book] ([id], [title], [description], [img], [quantity], [price], [author], [category_id], [status], [create_at], [update_at]) VALUES (1004, N'Journey to the East', N'2011 Reprint of 1957 English Translation. Full facsimile of the original edition, not reproduced with Optical Recognition Software. "Journey to the East" is written from the point of view of a man who becomes a member of "The League", a timeless religious sect whose members include famous fictional and real characters, such as Plato, Mozart, Pythagoras, Paul Klee, Don Quixote, Tristram Shandy, Baudelaire, and the ferryman Vasudeva, a character from one of Hesse''s earlier works, Siddhartha. A branch of the group goes on a pilgrimage to "the East" in search of the "ultimate Truth". The conclusion of the short novel is a stroke of Hesse''s typical Eastern mysticism at its finest. Hermann Hesse was born in Calw in the Black Forest on July 2, 1877, and from an early age was obsessed with the mystery of existence and humanity''s place in the Universe. The Journey to the East is Hesse''s tale of inner pilgrimage, an allegory on human desire for enlightenment and the long road that must be traveled to that ultimate goal. Using remarkably clear and accessible language, the book brings together the experience and conclusions of many years of spiritual struggle.', N'6560a409f40c37ac63d4e279bbea34b7.jpg', 40, CAST(5.00 AS Decimal(18, 2)), N'Nguyen Phong + Baird T. Spalding', 9, 1, CAST(N'2020-08-27 19:07:48.390' AS DateTime), CAST(N'2020-08-30 23:00:16.280' AS DateTime))
INSERT [dbo].[book] ([id], [title], [description], [img], [quantity], [price], [author], [category_id], [status], [create_at], [update_at]) VALUES (1005, N'You Don''t Know JS Yet', N'Welcome to the 2nd edition of the widely acclaimed You Don''t Know JS (YDKJS) book series: You Don''t Know JS Yet (YDKJSY).

If you''ve read any of the 1st edition books, you can expect a refreshed approach in these new ones, with plenty of updated coverage of what''s changed in JS over the last five years. But what I hope and believe you''ll still get is the same commitment to respecting JS and digging into what really makes it tick.

If this is your first time reading these books, I''m glad you''re here. Prepare for a deep and extensive journey into all the corners of JavaScript.

If you are new to programming or JS, be aware that these books are not intended as a gentle "intro to JavaScript." This material is, at times, complex and challenging, and goes much deeper than is typical for a first-time learner. You''re welcome here no matter what your background is, but these books are written assuming you''re already comfortable with JS and have at least 6Ã¢??9 months experience with it.', N'4.png', 50, CAST(27.00 AS Decimal(18, 2)), N'Kyle Simpson', 11, 1, CAST(N'2020-08-27 19:22:12.387' AS DateTime), CAST(N'2020-08-27 22:35:10.533' AS DateTime))
INSERT [dbo].[book] ([id], [title], [description], [img], [quantity], [price], [author], [category_id], [status], [create_at], [update_at]) VALUES (1006, N'Fullstack React With Typescript', N'Learn Pro Patterns for Hooks, Testing, Redux, SSR, and GraphQL ', N'5.png', 10, CAST(40.00 AS Decimal(18, 2)), N'Maksim Ivanov and Alex Bespoyasov Edited by Nate Murray', 11, 1, CAST(N'2020-08-27 19:28:51.467' AS DateTime), CAST(N'2020-08-30 15:40:21.557' AS DateTime))
INSERT [dbo].[book] ([id], [title], [description], [img], [quantity], [price], [author], [category_id], [status], [create_at], [update_at]) VALUES (1007, N'Clean Code', N'Even bad code can function. But if code isnâ??t clean, it can bring a development organization to its knees. Every year, countless hours and significant resources are lost because of poorly written code. But it doesnâ??t have to be that way.

Noted software expert Robert C. Martin presents a revolutionary paradigm with Clean Code: A Handbook of Agile Software Craftsmanship . Martin has teamed up with his colleagues from Object Mentor to distill their best agile practice of cleaning code â??on the flyâ? into a book that will instill within you the values of a software craftsman and make you a better programmerâ??but only if you work at it.

What kind of work will you be doing? Youâ??ll be reading codeâ??lots of code. And you will be challenged to think about whatâ??s right about that code, and whatâ??s wrong with it. More importantly, you will be challenged to reassess your professional values and your commitment to your craft.

Clean Code is divided into three parts. The first describes the principles, patterns, and practices of writing clean code. The second part consists of several case studies of increasing complexity. Each case study is an exercise in cleaning up codeâ??of transforming a code base that has some problems into one that is sound and efficient. The third part is the payoff: a single chapter containing a list of heuristics and â??smellsâ? gathered while creating the case studies. The result is a knowledge base that describes the way we think when we write, read, and clean code.

Readers will come away from this book understanding
How to tell the difference between good and bad code
How to write good code and how to transform bad code into good code
How to create good names, good functions, good objects, and good classes
How to format code for maximum readability
How to implement complete error handling without obscuring code logic
How to unit test and practice test-driven development
This book is a must for any developer, software engineer, project manager, team lead, or systems analyst with an interest in producing better code.', N'6.jpg', 40, CAST(33.00 AS Decimal(18, 2)), N'Robert Cecil Martin', 11, 1, CAST(N'2020-08-27 19:39:37.947' AS DateTime), CAST(N'2020-08-27 19:39:37.947' AS DateTime))
INSERT [dbo].[book] ([id], [title], [description], [img], [quantity], [price], [author], [category_id], [status], [create_at], [update_at]) VALUES (1009, N'Cracking the Coding Interview', N' am not a recruiter. I am a software engineer. And as such, I know what it''s like to be asked to whip up brilliant algorithms on the spot and then write flawless code on a whiteboard. I''ve been through this as a candidate and as an interviewer.

Cracking the Coding Interview, 6th Edition is here to help you through this process, teaching you what you need to know and enabling you to perform at your very best. I''ve coached and interviewed hundreds of software engineers. The result is this book.

Learn how to uncover the hints and hidden details in a question, discover how to break down a problem into manageable chunks, develop techniques to unstick yourself when stuck, learn (or re-learn) core computer science concepts, and practice on 189 interview questions and solutions.

These interview questions are real; they are not pulled out of computer science textbooks. They reflect what''s truly being asked at the top companies, so that you can be as prepared as possible. WHAT''S INSIDE?

189 programming interview questions, ranging from the basics to the trickiest algorithm problems.
A walk-through of how to derive each solution, so that you can learn how to get there yourself.
Hints on how to solve each of the 189 questions, just like what you would get in a real interview.
Five proven strategies to tackle algorithm questions, so that you can solve questions you haven''t seen.
Extensive coverage of essential topics, such as big O time, data structures, and core algorithms.
A behind the scenesÃ?Â look at how top companies like Google and Facebook hire developers.
Techniques to prepare for and ace the soft side of the interview: behavioral questions.
For interviewers and companies: details on what makes a good interview question and hiring process.
Illustrations noteIllustrations: Illustrations, black and white', N'7.jpg', 10, CAST(27.00 AS Decimal(18, 2)), N'Gayle Laakmann McDowell', 11, 1, CAST(N'2020-08-27 19:42:08.427' AS DateTime), CAST(N'2020-08-27 19:42:08.427' AS DateTime))
INSERT [dbo].[book] ([id], [title], [description], [img], [quantity], [price], [author], [category_id], [status], [create_at], [update_at]) VALUES (1010, N'Scrum: The Art of Doing Twice the Work in Half the Time', N'Please Read Notes: Brand New, International Softcover Edition, Printed in black and white pages, minor self wear on the cover or pages, Sale restriction may be printed on the book, but Book name, contents, and author are exactly same as Hardcover Edition. Fast delivery through DHL/FedEx express.', N'8.jpg', 33, CAST(16.00 AS Decimal(18, 2)), N'Jeff Sutherland', 11, 0, CAST(N'2020-08-27 19:53:21.817' AS DateTime), CAST(N'2020-08-30 19:11:04.210' AS DateTime))
INSERT [dbo].[book] ([id], [title], [description], [img], [quantity], [price], [author], [category_id], [status], [create_at], [update_at]) VALUES (1011, N'Learning Agile:Understanding Scrum, XP, Lean,and Kanban', N'Learning Agile is a comprehensive guide to the most popular agile methods, written in a light and engaging style that makes it easy for you to learn.

Agile has revolutionized the way teams approach software development, but with dozens of agile methodologies to choose from, the decision to "go agile" can be tricky. This practical book helps you sort it out, first by grounding you in agile''s underlying principles, then by describing four specific--and well-used--agile methods: Scrum, extreme programming (XP), Lean, and Kanban.

Each method focuses on a different area of development, but they all aim to change your team''s mindset--from individuals who simply follow a plan to a cohesive group that makes decisions together. Whether you''re considering agile for the first time, or trying it again, you''ll learn how to choose a method that best fits your team and your company.
Understand the purpose behind agile''s core values and principles
Learn Scrum''s emphasis on project management, self-organization, and collective commitment
Focus on software design and architecture with XP practices such as test-first and pair programming
Use Lean thinking to empower your team, eliminate waste, and deliver software fast
Learn how Kanban''s practices help you deliver great software by managing flow
Adopt agile practices and principles with an agile coach', N'9.png', 18, CAST(24.00 AS Decimal(18, 2)), N'Jennifer Greene & Andrew Stellman', 11, 1, CAST(N'2020-08-27 19:57:46.663' AS DateTime), CAST(N'2020-08-27 22:35:26.330' AS DateTime))
SET IDENTITY_INSERT [dbo].[book] OFF
SET IDENTITY_INSERT [dbo].[category] ON 

INSERT [dbo].[category] ([id], [name], [create_at], [update_at]) VALUES (1, N'Cultivation', CAST(N'2020-08-26 06:31:25.070' AS DateTime), CAST(N'2020-08-26 06:31:25.070' AS DateTime))
INSERT [dbo].[category] ([id], [name], [create_at], [update_at]) VALUES (2, N'Classics', CAST(N'2020-08-26 15:11:21.277' AS DateTime), CAST(N'2020-08-26 15:11:21.277' AS DateTime))
INSERT [dbo].[category] ([id], [name], [create_at], [update_at]) VALUES (3, N'Detective And Mystery', CAST(N'2020-08-26 15:12:05.910' AS DateTime), CAST(N'2020-08-26 15:12:05.910' AS DateTime))
INSERT [dbo].[category] ([id], [name], [create_at], [update_at]) VALUES (4, N'Art/architecture', CAST(N'2020-08-26 15:12:27.007' AS DateTime), CAST(N'2020-08-26 15:12:27.007' AS DateTime))
INSERT [dbo].[category] ([id], [name], [create_at], [update_at]) VALUES (5, N'Dictionary', CAST(N'2020-08-26 15:13:02.113' AS DateTime), CAST(N'2020-08-26 15:13:02.113' AS DateTime))
INSERT [dbo].[category] ([id], [name], [create_at], [update_at]) VALUES (6, N'Guide', CAST(N'2020-08-26 15:13:13.583' AS DateTime), CAST(N'2020-08-26 15:13:13.583' AS DateTime))
INSERT [dbo].[category] ([id], [name], [create_at], [update_at]) VALUES (7, N'Philosophy', CAST(N'2020-08-26 15:13:21.610' AS DateTime), CAST(N'2020-08-26 15:13:21.610' AS DateTime))
INSERT [dbo].[category] ([id], [name], [create_at], [update_at]) VALUES (8, N'Novel', CAST(N'2020-08-26 15:38:51.747' AS DateTime), CAST(N'2020-08-26 15:38:51.747' AS DateTime))
INSERT [dbo].[category] ([id], [name], [create_at], [update_at]) VALUES (9, N'New Age & Spirituality', CAST(N'2020-08-27 12:05:09.367' AS DateTime), CAST(N'2020-08-27 12:05:09.367' AS DateTime))
INSERT [dbo].[category] ([id], [name], [create_at], [update_at]) VALUES (10, N'Occult & Paranormal', CAST(N'2020-08-27 12:06:41.373' AS DateTime), CAST(N'2020-08-27 12:06:41.373' AS DateTime))
INSERT [dbo].[category] ([id], [name], [create_at], [update_at]) VALUES (11, N'Software Development', CAST(N'2020-08-27 12:39:19.560' AS DateTime), CAST(N'2020-08-27 12:39:19.560' AS DateTime))
SET IDENTITY_INSERT [dbo].[category] OFF
SET IDENTITY_INSERT [dbo].[discount] ON 

INSERT [dbo].[discount] ([id], [offer], [isUsed], [status], [create_at], [update_at], [code]) VALUES (1, 25, 0, 1, CAST(N'2020-08-30 06:43:49.707' AS DateTime), CAST(N'2020-08-30 06:43:49.707' AS DateTime), N'aaaa')
INSERT [dbo].[discount] ([id], [offer], [isUsed], [status], [create_at], [update_at], [code]) VALUES (2, 10, 1, 1, CAST(N'2020-08-30 07:01:52.100' AS DateTime), CAST(N'2020-08-30 15:39:47.993' AS DateTime), N'bbbb')
INSERT [dbo].[discount] ([id], [offer], [isUsed], [status], [create_at], [update_at], [code]) VALUES (3, 32, 1, 1, CAST(N'2020-08-30 21:23:19.637' AS DateTime), CAST(N'2020-08-30 21:31:09.490' AS DateTime), N'e3jAGvcXiL')
INSERT [dbo].[discount] ([id], [offer], [isUsed], [status], [create_at], [update_at], [code]) VALUES (4, 97, 1, 1, CAST(N'2020-08-30 22:14:14.927' AS DateTime), CAST(N'2020-08-30 22:15:20.843' AS DateTime), N'generatecode')
SET IDENTITY_INSERT [dbo].[discount] OFF
SET IDENTITY_INSERT [dbo].[role] ON 

INSERT [dbo].[role] ([id], [name], [create_at], [update_at]) VALUES (1, N'admin', CAST(N'2020-08-25 16:07:19.583' AS DateTime), CAST(N'2020-08-25 16:07:19.583' AS DateTime))
INSERT [dbo].[role] ([id], [name], [create_at], [update_at]) VALUES (2, N'student', CAST(N'2020-08-25 16:08:04.277' AS DateTime), CAST(N'2020-08-25 16:08:04.277' AS DateTime))
INSERT [dbo].[role] ([id], [name], [create_at], [update_at]) VALUES (3, N'teacher', CAST(N'2020-08-25 16:08:09.567' AS DateTime), CAST(N'2020-08-25 16:08:09.567' AS DateTime))
INSERT [dbo].[role] ([id], [name], [create_at], [update_at]) VALUES (4, N'officer', CAST(N'2020-08-25 16:08:12.807' AS DateTime), CAST(N'2020-08-25 16:08:12.807' AS DateTime))
INSERT [dbo].[role] ([id], [name], [create_at], [update_at]) VALUES (5, N'it support', CAST(N'2020-08-25 16:54:39.673' AS DateTime), CAST(N'2020-08-25 16:54:39.673' AS DateTime))
SET IDENTITY_INSERT [dbo].[role] OFF
SET IDENTITY_INSERT [dbo].[user] ON 

INSERT [dbo].[user] ([id], [email], [encrypted_password], [name], [phone], [role_id], [img], [status], [create_at], [update_at]) VALUES (1, N'admin@gmail.com', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Van Thong', N'0935403639', 1, N'IMG_0887.JPG', 0, CAST(N'2020-08-25 16:10:06.133' AS DateTime), CAST(N'2020-08-26 00:14:47.640' AS DateTime))
INSERT [dbo].[user] ([id], [email], [encrypted_password], [name], [phone], [role_id], [img], [status], [create_at], [update_at]) VALUES (2, N'leonardo@vinci.it', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Leonardo Davinci', N'+199999', 3, N'nhung-kiet-tac-hoi-hoa-lam-dau-dau-hau-the-cua-leonardo-da-vinci.jpg', 1, CAST(N'2020-08-25 16:12:14.907' AS DateTime), CAST(N'2020-08-25 16:12:14.907' AS DateTime))
INSERT [dbo].[user] ([id], [email], [encrypted_password], [name], [phone], [role_id], [img], [status], [create_at], [update_at]) VALUES (3, N'raphael@renaissance.era', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Raphael', N'+199988877', 3, N'Raphael.jpg', 1, CAST(N'2020-08-25 16:52:05.597' AS DateTime), CAST(N'2020-08-25 16:52:05.597' AS DateTime))
INSERT [dbo].[user] ([id], [email], [encrypted_password], [name], [phone], [role_id], [img], [status], [create_at], [update_at]) VALUES (5, N'ivanka@heaven.land', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Ivanka ', N'+01999999', 2, N'Last-Button-16x20-1200.jpg', 1, CAST(N'2020-08-25 16:52:41.333' AS DateTime), CAST(N'2020-08-25 16:52:41.333' AS DateTime))
INSERT [dbo].[user] ([id], [email], [encrypted_password], [name], [phone], [role_id], [img], [status], [create_at], [update_at]) VALUES (6, N'psykhe@heaven.land', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'psykhe', N'+99999999', 4, N'4905ddf66fe1c1199ffa2939b9e9caad--pink-roses-beautiful-women.jpg', 1, CAST(N'2020-08-25 16:53:07.530' AS DateTime), CAST(N'2020-08-25 16:53:07.530' AS DateTime))
INSERT [dbo].[user] ([id], [email], [encrypted_password], [name], [phone], [role_id], [img], [status], [create_at], [update_at]) VALUES (7, N'anastasia@ana.miss', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Anastasia', N'+22222222', 4, N'moroni1.jpg', 1, CAST(N'2020-08-25 16:53:30.917' AS DateTime), CAST(N'2020-08-25 16:53:30.917' AS DateTime))
INSERT [dbo].[user] ([id], [email], [encrypted_password], [name], [phone], [role_id], [img], [status], [create_at], [update_at]) VALUES (10, N'santos@cesar.man', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'cesar santos', N'cesar santos', 5, N'cesar-santos.jpg', 1, CAST(N'2020-08-25 16:54:44.157' AS DateTime), CAST(N'2020-08-25 16:54:44.157' AS DateTime))
INSERT [dbo].[user] ([id], [email], [encrypted_password], [name], [phone], [role_id], [img], [status], [create_at], [update_at]) VALUES (11, N'leo@sobre.tela', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Leo Sobre Tela', N'+79797979', 4, N'Handmade-retrato-pintura-a-leo-sobre-tela-cl-ssico-sem-moldura.jpg_640x640.jpg', 1, CAST(N'2020-08-25 16:55:08.653' AS DateTime), CAST(N'2020-08-25 16:55:08.653' AS DateTime))
SET IDENTITY_INSERT [dbo].[user] OFF
ALTER TABLE [dbo].[checkout] ADD  CONSTRAINT [DF_checkout_create_at]  DEFAULT (sysutcdatetime()) FOR [create_at]
GO
ALTER TABLE [dbo].[checkout] ADD  CONSTRAINT [DF_checkout_update_at]  DEFAULT (sysutcdatetime()) FOR [update_at]
GO
ALTER TABLE [dbo].[shopping] ADD  CONSTRAINT [DF_shopping_create_at]  DEFAULT (sysutcdatetime()) FOR [create_at]
GO
ALTER TABLE [dbo].[shopping] ADD  CONSTRAINT [DF_shopping_update_at]  DEFAULT (sysutcdatetime()) FOR [update_at]
GO
ALTER TABLE [dbo].[shopping] ADD  CONSTRAINT [DF_shopping_status]  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[shopping_book] ADD  CONSTRAINT [DF_shopping_book_status]  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[book]  WITH CHECK ADD  CONSTRAINT [FK_book_category] FOREIGN KEY([category_id])
REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE [dbo].[book] CHECK CONSTRAINT [FK_book_category]
GO
ALTER TABLE [dbo].[checkout]  WITH CHECK ADD  CONSTRAINT [FK_checkout_discount] FOREIGN KEY([discount_id])
REFERENCES [dbo].[discount] ([id])
GO
ALTER TABLE [dbo].[checkout] CHECK CONSTRAINT [FK_checkout_discount]
GO
ALTER TABLE [dbo].[checkout]  WITH CHECK ADD  CONSTRAINT [FK_checkout_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[checkout] CHECK CONSTRAINT [FK_checkout_user]
GO
ALTER TABLE [dbo].[checkout_book]  WITH CHECK ADD  CONSTRAINT [FK_checkout_book_book] FOREIGN KEY([book_id])
REFERENCES [dbo].[book] ([id])
GO
ALTER TABLE [dbo].[checkout_book] CHECK CONSTRAINT [FK_checkout_book_book]
GO
ALTER TABLE [dbo].[checkout_book]  WITH CHECK ADD  CONSTRAINT [FK_checkout_book_checkout] FOREIGN KEY([checkout_id])
REFERENCES [dbo].[checkout] ([id])
GO
ALTER TABLE [dbo].[checkout_book] CHECK CONSTRAINT [FK_checkout_book_checkout]
GO
ALTER TABLE [dbo].[shopping]  WITH CHECK ADD  CONSTRAINT [FK_shopping_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[shopping] CHECK CONSTRAINT [FK_shopping_user]
GO
ALTER TABLE [dbo].[shopping_book]  WITH CHECK ADD  CONSTRAINT [FK_shopping_book_book] FOREIGN KEY([book_id])
REFERENCES [dbo].[book] ([id])
GO
ALTER TABLE [dbo].[shopping_book] CHECK CONSTRAINT [FK_shopping_book_book]
GO
ALTER TABLE [dbo].[shopping_book]  WITH CHECK ADD  CONSTRAINT [FK_shopping_book_shopping] FOREIGN KEY([shopping_id])
REFERENCES [dbo].[shopping] ([id])
GO
ALTER TABLE [dbo].[shopping_book] CHECK CONSTRAINT [FK_shopping_book_shopping]
GO
ALTER TABLE [dbo].[user]  WITH CHECK ADD  CONSTRAINT [FK_user_role] FOREIGN KEY([role_id])
REFERENCES [dbo].[role] ([id])
GO
ALTER TABLE [dbo].[user] CHECK CONSTRAINT [FK_user_role]
GO
USE [master]
GO
ALTER DATABASE [lab02] SET  READ_WRITE 
GO
